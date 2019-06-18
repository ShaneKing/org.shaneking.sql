/*
 * @(#)SKEntity.java		Created at 2017/9/10
 *
 * Copyright (c) ShaneKing All rights reserved.
 * ShaneKing PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.shaneking.sql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.skava.ling.collect.Tuple;
import org.shaneking.skava.ling.lang.String0;
import org.shaneking.skava.ling.lang.String20;
import org.shaneking.sql.Keyword0;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.PageHelper;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Accessors(chain = true)
@Slf4j
@ToString
public class SKEntity<J> {
  @Transient
  public static final String APPEND_AND = " and ";
  @Transient
  public static final String APPEND_WHERE = " where ";
  @Getter
  @JsonIgnore
  @Transient
  private final Map<String, Column> columnMap = Maps.newHashMap();
  @Getter
  @JsonIgnore
  @Transient
  private final Map<String, String> dbColumnMap = Maps.newHashMap();
  @Getter
  @JsonIgnore
  @Transient
  private final Map<String, Field> fieldMap = Maps.newHashMap();
  @Getter
  @JsonIgnore
  @Transient
  private final List<String> fieldNameList = Lists.newArrayList();
  @Getter
  @JsonIgnore
  @Transient
  private final List<String> idFieldNameList = Lists.newLinkedList();
  @Getter
  @JsonIgnore
  @Transient
  private final List<String> versionFieldNameList = Lists.newLinkedList();

  @Getter
  @JsonIgnore
  @Setter
  @Transient
  private String fullTableName;
  @Getter
  @JsonIgnore
  @Setter
  @Transient
  private Table table;

  /**
   * J maybe fastjson,gson,jackson...
   * <blockquote><pre>
   *     {
   *         createDatetime:{
   *             op:'between',
   *             c:['2017-09-10','2019-04-27']
   *         },
   *         invalidDatetime:{
   *             op:'>',
   *             c:'2017-09-10'
   *         },
   *         lastModifyDatetime:{
   *             op:'like',
   *             c:'2019-04-27'
   *         }
   *     }
   * </pre></blockquote>
   */
  @Getter
  @Setter
  @Transient
  private J whereJson;

  @Getter
  @Setter
  @Transient
  private PageHelper pageHelper = new PageHelper();

  public SKEntity() {
    initTableInfo();
    initColumnInfo(this.getClass());
  }

  private String createColumnStatement(String columnName, boolean idOrVersion) {
    String rtn = null;
    Field columnField = this.getFieldMap().get(columnName);
    String columnFieldTypeString = columnField.getType().getCanonicalName();
    String columnDbTypeString = null;
    String partNotNull = (idOrVersion || !this.getColumnMap().get(columnName).nullable()) ? " not null" : String0.EMPTY;
    if (columnField.getAnnotation(Lob.class) != null) {
      columnDbTypeString = Keyword0.TYPE_LONGTEXT;
    } else if (Integer.class.getCanonicalName().equals(columnFieldTypeString)) {
      columnDbTypeString = Keyword0.TYPE_INT;
    } else {
      columnDbTypeString = Keyword0.TYPE_VARCHAR;
    }
    String[] comments = Strings.nullToEmpty(this.getColumnMap().get(columnName).columnDefinition()).split(Keyword0.COMMENT);
    String comment = comments.length > 1 ? comments[1] : " ''";
    if (Keyword0.TYPE_LONGTEXT.equals(columnDbTypeString) || Keyword0.TYPE_INT.equals(columnDbTypeString)) {
      rtn = MessageFormat.format("  `{0}` {1}{2} comment{3},", this.getDbColumnMap().get(columnName), columnDbTypeString, partNotNull, comment);
    } else {
      rtn = MessageFormat.format("  `{0}` {1}({2}){3} comment{4},", this.getDbColumnMap().get(columnName), columnDbTypeString, String.valueOf(this.getColumnMap().get(columnName).length()), partNotNull, comment);
    }
    return rtn;
  }

  public String createIndexSql() {
    List<String> indexList = Lists.newArrayList(this.getTable().uniqueConstraints()).parallelStream().map(uniqueConstraint -> {
      String schema = this.getTable().schema();
      String indexName = "u_idx_" + Joiner.on("_").join(uniqueConstraint.columnNames());
      String indexColumns = "`" + Joiner.on("` asc, `").join(uniqueConstraint.columnNames()) + "` asc";
      return Strings.isNullOrEmpty(schema) ? MessageFormat.format("alter table `{0}` add unique index `{1}` ({2});", this.getFullTableName(), indexName, indexColumns) : MessageFormat.format("alter table `{0}`.`{1}` add unique index `{2}` ({3});", schema, this.getFullTableName().split("\\.")[1], indexName, indexColumns);
    }).collect(Collectors.toList());
    indexList.addAll(fieldNameList.parallelStream().filter(fieldName -> columnMap.get(fieldName).unique()).map(fieldName -> {
      String schema = this.getTable().schema();
      String indexName = "u_idx_" + dbColumnMap.get(fieldName);
      String indexColumns = "`" + dbColumnMap.get(fieldName) + "` asc";
      return Strings.isNullOrEmpty(schema) ? MessageFormat.format("alter table `{0}` add unique index `{1}` ({2});", this.getFullTableName(), indexName, indexColumns) : MessageFormat.format("alter table `{0}`.`{1}` add unique index `{2}` ({3});", schema, this.getFullTableName().split("\\.")[1], indexName, indexColumns);
    }).collect(Collectors.toList()));
    return Joiner.on("\n").join(indexList);
  }

  public String createTableSql() {
    List<String> sqlList = Lists.newArrayList();
    String schema = this.getTable().schema();
    if (Strings.isNullOrEmpty(schema)) {
      sqlList.add(MessageFormat.format("create table `{0}` (", this.getFullTableName()));
    } else {
      sqlList.add(MessageFormat.format("create table `{0}`.`{1}` (", schema, this.getFullTableName().substring(schema.length() + 1)));
    }
    for (String versionColumn : this.getVersionFieldNameList()) {
      sqlList.add(this.createColumnStatement(versionColumn, true));
    }
    for (String idColumn : this.getIdFieldNameList()) {
      sqlList.add(this.createColumnStatement(idColumn, true));
    }
    for (String columnName : this.getFieldNameList()) {
      if (this.getIdFieldNameList().indexOf(columnName) == -1 && this.getVersionFieldNameList().indexOf(columnName) == -1) {
        sqlList.add(this.createColumnStatement(columnName, false));
      }
    }
    sqlList.add(MessageFormat.format("  primary key (`{0}`)", Joiner.on("`,`").join(this.getIdFieldNameList().stream().map(idFieldName -> this.getDbColumnMap().get(idFieldName)).collect(Collectors.toList()))));
    sqlList.add(String0.CLOSE_PARENTHESIS + String0.SEMICOLON);
    return Joiner.on("\n").join(sqlList);
  }

  public List<OperationContent> findOperationContentList(String fieldName) {
    List<OperationContent> rtnList = Lists.newArrayList();
    //implements by sub entity
    return rtnList;
  }

  public void initColumnInfo(Class<? extends Object> skEntityClass) {
    if (SKEntity.class.isAssignableFrom(skEntityClass.getSuperclass())) {
      initColumnInfo(skEntityClass.getSuperclass());
    }
    for (Field field : skEntityClass.getDeclaredFields()) {
      if (field.getAnnotation(Transient.class) == null) {
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
          this.getColumnMap().put(field.getName(), column);
          this.getFieldMap().put(field.getName(), field);
          this.getDbColumnMap().put(field.getName(), Strings.isNullOrEmpty(column.name()) ? String0.upper2lower(field.getName()) : column.name());
          if (this.getFieldNameList().indexOf(field.getName()) == -1) {
            this.getFieldNameList().add(field.getName());
          }
        }
        if (field.getAnnotation(Id.class) != null && this.getIdFieldNameList().indexOf(field.getName()) == -1) {
          this.getIdFieldNameList().add(field.getName());
        }
        if (field.getAnnotation(Version.class) != null && this.getVersionFieldNameList().indexOf(field.getName()) == -1) {
          this.getVersionFieldNameList().add(field.getName());
        }
      }
    }
  }

  public void initTableInfo() {
    if (this.getTable() == null) {
      this.setTable(this.getClass().getAnnotation(Table.class));
    }
    this.setFullTableName(Strings.isNullOrEmpty(this.getTable().schema()) ? String0.EMPTY : this.getTable().schema() + String0.DOT);
    if (Strings.isNullOrEmpty(this.getTable().name())) {
      String classTableName = String0.upper2lower(Lists.reverse(Lists.newArrayList(this.getClass().getName().split(String20.BACKSLASH_DOT))).get(0));
      this.setFullTableName(this.getFullTableName() + "t" + (classTableName.startsWith(String0.UNDERLINE) ? classTableName : String0.UNDERLINE + classTableName));
    } else {
      this.setFullTableName(this.getFullTableName() + this.getTable().name());
    }
  }

  public void mapRow(ResultSet rs) {
    String columnFieldTypeString = null;
    Object o = null;
    for (String fieldName : this.getFieldNameList()) {
      try {
        columnFieldTypeString = this.getFieldMap().get(fieldName).getType().getCanonicalName();
        if (Integer.class.getCanonicalName().equals(columnFieldTypeString)) {
          o = rs.getInt(this.getDbColumnMap().get(fieldName));
        } else {
          o = rs.getString(this.getDbColumnMap().get(fieldName));
        }
        if (o != null) {
          this.getClass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), o.getClass()).invoke(this, o);
        }
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }
    }
  }

  //curd
  public Tuple.Pair<String, List<Object>> countSql() {
    return selectSql(Lists.newArrayList("count(1)"), null);
  }

  public int delete() {
    int rtnInt = 0;
    //implements by sub entity
    return rtnInt;
  }

  public Tuple.Pair<String, List<Object>> deleteByIdSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> whereIdList = Lists.newArrayList();
    whereStatement(whereIdList, rtnObjectList, this.getIdFieldNameList());
    whereStatementExt(whereIdList, rtnObjectList, this.getIdFieldNameList());

    List<String> sqlList = Lists.newArrayList();
    sqlList.add("delete from");
    sqlList.add(this.getFullTableName());
    if (whereIdList.size() > 0) {
      sqlList.add("where");
      sqlList.add(Joiner.on(APPEND_AND).join(whereIdList));
    }

    return Tuple.of(Joiner.on(String0.BLACK).join(sqlList), rtnObjectList);
  }

  public Tuple.Pair<String, List<Object>> deleteByIdAndVersionSql() {
    return this.deleteOrUpdateByIdAndVersionSql(this.deleteByIdSql());
  }

  public Tuple.Pair<String, List<Object>> deleteOrUpdateByIdAndVersionSql(@NonNull Tuple.Pair<String, List<Object>> deleteOrUpdateByIdSql) {
    Tuple.Pair<String, List<Object>> rtn = deleteOrUpdateByIdSql;
    StringBuffer rtnSql = new StringBuffer(Tuple.getFirst(rtn));
    List<Object> rtnObjectList = Tuple.getSecond(rtn);

    List<String> whereVersionList = Lists.newArrayList();
    whereStatement(whereVersionList, rtnObjectList, this.getVersionFieldNameList());
    whereStatementExt(whereVersionList, rtnObjectList, this.getVersionFieldNameList());
    if (whereVersionList.size() > 0) {
      if (rtnSql.indexOf(APPEND_WHERE) == -1) {
        rtnSql.append(APPEND_WHERE);
      } else {
        rtnSql.append(APPEND_AND);
      }
      rtnSql.append(Joiner.on(APPEND_AND).join(whereVersionList));
    }
    return Tuple.of(rtnSql.toString(), rtnObjectList);
  }

  public int insert() {
    int rtnInt = 0;
    //implements by sub entity
    return rtnInt;
  }

  public int insertOrUpdateById() {
    int rtnInt = 0;
    //implements by sub entity
    return rtnInt;
  }

  public Tuple.Pair<String, List<Object>> insertSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> insertList = Lists.newArrayList();
    insertStatement(insertList, rtnObjectList);
    insertStatementExt(insertList, rtnObjectList);

    List<String> sqlList = Lists.newArrayList();
    sqlList.add("insert into");
    sqlList.add(this.getFullTableName());
    sqlList.add(String0.OPEN_PARENTHESIS + Joiner.on(String0.COMMA).join(insertList) + String0.CLOSE_PARENTHESIS);
    sqlList.add("values");
    sqlList.add(String0.OPEN_PARENTHESIS + Strings.repeat(String0.COMMA + String0.QUESTION, insertList.size()).substring(1) + String0.CLOSE_PARENTHESIS);

    return Tuple.of(Joiner.on(String0.BLACK).join(sqlList), rtnObjectList);
  }

  public void insertStatement(@NonNull List<String> insertList, @NonNull List<Object> objectList) {
    Object o;
    for (String fieldName : this.getFieldNameList()) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.warn(e.toString());
      }
      if (o != null && !Strings.isNullOrEmpty(o.toString())) {
        insertList.add(this.getDbColumnMap().get(fieldName));
        objectList.add(o);
      }
    }
  }

  public void insertStatementExt(@NonNull List<String> insertList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public List<? extends SKEntity> select() {
    List<? extends SKEntity> rtnList = Lists.newArrayList();
    //implements by sub entity
    return rtnList;
  }

  public Tuple.Pair<String, List<Object>> selectSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> selectList = Lists.newArrayList();
    selectStatement(selectList, rtnObjectList);
    selectStatementExt(selectList, rtnObjectList);

    return selectSql(selectList, rtnObjectList);
  }

  public Tuple.Pair<String, List<Object>> selectSql(@NonNull List<String> selectList, List<Object> selectObjectList) {
    List<Object> rtnObjectList = Lists.newArrayList();

    if (selectObjectList != null) {
      rtnObjectList.addAll(selectObjectList);
    }

    List<String> fromList = Lists.newArrayList();
    fromStatement(fromList, rtnObjectList);
    fromStatementExt(fromList, rtnObjectList);

    List<String> whereList = Lists.newArrayList();
    whereStatement(whereList, rtnObjectList);
    whereStatementExt(whereList, rtnObjectList);

    List<String> groupByList = Lists.newArrayList();
    groupByStatement(groupByList, rtnObjectList);
    groupByStatementExt(groupByList, rtnObjectList);

    List<String> havingList = Lists.newArrayList();
    havingStatement(havingList, rtnObjectList);
    havingStatementExt(havingList, rtnObjectList);

    List<String> orderByList = Lists.newArrayList();
    orderByStatement(orderByList, rtnObjectList);
    orderByStatementExt(orderByList, rtnObjectList);

    return selectSql(rtnObjectList, selectList, fromList, whereList, groupByList, havingList, orderByList);
  }

  public Tuple.Pair<String, List<Object>> selectSql(@NonNull List<String> selectList, List<Object> selectObjectList, @NonNull List<String> fromList, List<Object> fromObjectList, List<String> whereList, List<Object> whereObjectList, List<String> groupByList, List<Object> groupByObjectList, List<String> havingList, List<Object> havingObjectList, List<String> orderByList, List<Object> orderByObjectList) {
    List<Object> rtnObjectList = Lists.newArrayList();

    if (selectObjectList != null) {
      rtnObjectList.addAll(selectObjectList);
    }
    if (fromObjectList != null) {
      rtnObjectList.addAll(fromObjectList);
    }
    if (whereObjectList != null) {
      rtnObjectList.addAll(whereObjectList);
    }
    if (groupByObjectList != null) {
      rtnObjectList.addAll(groupByObjectList);
    }
    if (havingObjectList != null) {
      rtnObjectList.addAll(havingObjectList);
    }
    if (orderByObjectList != null) {
      rtnObjectList.addAll(orderByObjectList);
    }

    return selectSql(rtnObjectList, selectList, fromList, whereList, groupByList, havingList, orderByList);
  }

  public Tuple.Pair<String, List<Object>> selectSql(@NonNull List<Object> objectList, @NonNull List<String> selectList, @NonNull List<String> fromList, List<String> whereList, List<String> groupByList, List<String> havingList, List<String> orderByList) {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> sqlList = Lists.newArrayList();
    sqlList.add("select");
    sqlList.add(Joiner.on(String0.COMMA).join(selectList));
    sqlList.add("from");
    sqlList.add(Joiner.on(String0.COMMA).join(fromList));
    if (whereList != null && whereList.size() > 0) {
      sqlList.add("where");
      sqlList.add(Joiner.on(APPEND_AND).join(whereList));
    }
    if (groupByList != null && groupByList.size() > 0) {
      sqlList.add("group by");
      sqlList.add(Joiner.on(String0.COMMA).join(groupByList));
    }
    if (havingList != null && havingList.size() > 0) {
      sqlList.add("having");
      sqlList.add(Joiner.on(APPEND_AND).join(havingList));
    }
    if (orderByList != null && orderByList.size() > 0) {
      sqlList.add("order by");
      sqlList.add(Joiner.on(String0.COMMA).join(orderByList));
    }
    rtnObjectList.addAll(objectList);

    List<String> limitAndOffsetList = Lists.newArrayList();
    limitAndOffsetStatement(limitAndOffsetList, rtnObjectList);
    limitAndOffsetStatementExt(limitAndOffsetList, rtnObjectList);
    if (limitAndOffsetList.size() > 0) {
      sqlList.add(Joiner.on(String0.BLACK).join(limitAndOffsetList));
    }

    return Tuple.of(Joiner.on(String0.BLACK).join(sqlList), rtnObjectList);
  }

  public void selectStatement(@NonNull List<String> selectList, @NonNull List<Object> objectList) {
    selectList.addAll(this.getFieldNameList().stream().map((String fieldName) -> this.getDbColumnMap().get(fieldName)).collect(Collectors.toList()));
  }

  public void selectStatementExt(@NonNull List<String> selectList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public int update() {
    int rtnInt = 0;
    //implements by sub entity
    return rtnInt;
  }

  public Tuple.Pair<String, List<Object>> updateByIdSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> updateList = Lists.newArrayList();
    updateStatement(updateList, rtnObjectList);
    updateStatementExt(updateList, rtnObjectList);

    List<String> whereIdList = Lists.newArrayList();
    whereStatement(whereIdList, rtnObjectList, this.getIdFieldNameList());
    whereStatementExt(whereIdList, rtnObjectList, this.getIdFieldNameList());

    List<String> sqlList = Lists.newArrayList();
    sqlList.add("update");
    sqlList.add(this.getFullTableName());
    sqlList.add("set");
    sqlList.add(Joiner.on(String0.COMMA).join(updateList));
    if (whereIdList.size() > 0) {
      sqlList.add("where");
      sqlList.add(Joiner.on(APPEND_AND).join(whereIdList));
    }

    return Tuple.of(Joiner.on(String0.BLACK).join(sqlList), rtnObjectList);
  }

  public Tuple.Pair<String, List<Object>> updateByIdAndVersionSql() {
    return this.deleteOrUpdateByIdAndVersionSql(this.updateByIdSql());
  }

  public void updateStatement(@NonNull List<String> updateList, @NonNull List<Object> objectList) {
    Object o = null;
    for (String fieldName : this.getFieldNameList().stream().filter(fieldName -> this.getIdFieldNameList().indexOf(fieldName) == -1 && this.getColumnMap().get(fieldName).updatable()).collect(Collectors.toList())) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.warn(e.toString());
      }
      if (o != null) {//can update to empty
        updateList.add(this.getDbColumnMap().get(fieldName) + String20.EQUAL_QUESTION);
        if (this.getVersionFieldNameList().indexOf(fieldName) == -1) {
          objectList.add(o);
        } else {
          objectList.add((Integer) o + 1);
        }
      }
    }
  }

  public void updateStatementExt(@NonNull List<String> updateList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  //others
  public void fromStatement(@NonNull List<String> fromList, @NonNull List<Object> objectList) {
    fromList.add(this.getFullTableName());
  }

  public void fromStatementExt(@NonNull List<String> fromList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public void groupByStatement(@NonNull List<String> groupByList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public void groupByStatementExt(@NonNull List<String> groupByList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public void havingStatement(@NonNull List<String> havingByList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public void havingStatementExt(@NonNull List<String> havingByList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public void limitAndOffsetStatement(@NonNull List<String> limitAndOffsetList, @NonNull List<Object> objectList) {
    //implements by sub entity
    if (this.getPageHelper().getLimit() != null) {
      limitAndOffsetList.add(MessageFormat.format("limit {0}", this.getPageHelper().getLimit()));
    }
    if (this.getPageHelper().getOffset() != null) {
      limitAndOffsetList.add(MessageFormat.format("offset {0}", this.getPageHelper().getOffset()));
    }
  }

  public void limitAndOffsetStatementExt(@NonNull List<String> limitAndOffsetList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public void orderByStatement(@NonNull List<String> orderByList, @NonNull List<Object> objectList) {
  }

  public void orderByStatementExt(@NonNull List<String> orderByList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public void whereStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList) {
    this.whereStatement(whereList, objectList, this.getFieldNameList());
  }

  public void whereStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    Object o = null;
    for (String fieldName : fieldNameList) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.warn(e.toString());
      }
      if (this.getColumnMap().get(fieldName) != null) {
        if (o != null && !Strings.isNullOrEmpty(o.toString())) {
          whereList.add(this.getDbColumnMap().get(fieldName) + String20.EQUAL_QUESTION);
          objectList.add(o);
        }
        for (OperationContent oc : this.findOperationContentList(fieldName)) {
          if (Keyword0.BETWEEN.equalsIgnoreCase(oc.getOp())) {
            if (oc.getCl() != null && oc.getCl().size() == 2) {
              whereList.add(this.getDbColumnMap().get(fieldName) + String0.BLACK + oc.getOp() + String0.BLACK + String0.QUESTION + String0.BLACK + Keyword0.AND + String0.BLACK + String0.QUESTION);
              objectList.addAll(oc.getCl());
            }
          } else if (Keyword0.IN.equalsIgnoreCase(oc.getOp())) {
            if (oc.getCl() != null && oc.getCl().size() > 0) {
              whereList.add(this.getDbColumnMap().get(fieldName) + String0.BLACK + oc.getOp() + String0.BLACK + String0.OPEN_PARENTHESIS + Joiner.on(String0.COMMA).join(Collections.nCopies(oc.getCl().size(), String0.QUESTION)) + String0.CLOSE_PARENTHESIS);
              objectList.addAll(oc.getCl());
            }
          } else {
            whereList.add(this.getDbColumnMap().get(fieldName) + String0.BLACK + oc.getOp() + String0.BLACK + String0.QUESTION);
            objectList.add(Strings.nullToEmpty(oc.getBw()) + oc.getCs() + oc.getEw());
          }
        }
      }
    }
  }

  public void whereStatementExt(@NonNull List<String> whereList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public void whereStatementExt(@NonNull List<String> whereList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    //implements by sub entity
  }
}
