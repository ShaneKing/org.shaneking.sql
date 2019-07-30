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
import org.shaneking.skava.ling.lang.Integer0;
import org.shaneking.skava.ling.lang.String0;
import org.shaneking.skava.ling.lang.String20;
import org.shaneking.skava.sk.collect.Tuple;
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

/**
 * Design for has cache want single table operation
 */
@Accessors(chain = true)
@Slf4j
@ToString
public class SKEntity<J> {
  @Transient
  private static final String EMPTY_COMMENT_WITH_BLACK_PREFIX = " ''";
  @Transient
  private static final String UNIQUE_INDEX_NAME_PREFIX = "u_idx_";
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
  private String dbTableName;
  @Getter
  @Setter
  @Transient
  private PageHelper pageHelper;
  @Getter
  @JsonIgnore
  @Setter
  @Transient
  private Table javaTable;
  /**
   * I don't want Map<String, OperationContent> to limit everyone, J maybe Map/fastjson/gson/jackson...
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
  private J whereOCs;

  public SKEntity() {
    initTableInfo();
    initColumnInfo(this.getClass());
  }

  private String createColumnStatement(String columnName, boolean idOrVersion) {
    String rtn, columnDbTypeString;
    Field columnField = this.getFieldMap().get(columnName);
    String partNotNull = (idOrVersion || !this.getColumnMap().get(columnName).nullable()) ? Keyword0.NOT_NULL_WITH_BLACK_PREFIX : String0.EMPTY;
    if (columnField.getAnnotation(Lob.class) != null) {
      columnDbTypeString = Keyword0.TYPE_LONGTEXT;
    } else if (Integer.class.getCanonicalName().equals(columnField.getType().getCanonicalName())) {
      columnDbTypeString = Keyword0.TYPE_INT;
    } else {
      columnDbTypeString = Keyword0.TYPE_VARCHAR;
    }
    String[] comments = Strings.nullToEmpty(this.getColumnMap().get(columnName).columnDefinition()).split(Keyword0.COMMENT4ANNOTATION);
    String commentWithBlackPrefix = comments.length > 1 ? comments[1] : EMPTY_COMMENT_WITH_BLACK_PREFIX;
    if (Keyword0.TYPE_LONGTEXT.equals(columnDbTypeString) || Keyword0.TYPE_INT.equals(columnDbTypeString)) {
      rtn = MessageFormat.format("  `{0}` {1}{2} {3}{4},", this.getDbColumnMap().get(columnName), columnDbTypeString, partNotNull, Keyword0.COMMENT, commentWithBlackPrefix);
    } else {
      rtn = MessageFormat.format("  `{0}` {1}({2}){3} {4}{5},", this.getDbColumnMap().get(columnName), columnDbTypeString, String.valueOf(this.getColumnMap().get(columnName).length()), partNotNull, Keyword0.COMMENT, commentWithBlackPrefix);
    }
    return rtn;
  }

  public String createIndexSql() {
    Map<String, List<String>> idxPartNameColumnsMap = Maps.newHashMap();
    Lists.newArrayList(this.getJavaTable().uniqueConstraints()).stream().filter(uniqueConstraint -> uniqueConstraint.columnNames().length > 0).forEach(uniqueConstraint -> {
      idxPartNameColumnsMap.put(Joiner.on(String0.UNDERLINE).join(uniqueConstraint.columnNames()), Lists.newArrayList(uniqueConstraint.columnNames()));
    });
    this.getFieldNameList().stream().filter(fieldName -> this.getColumnMap().get(fieldName).unique()).forEach(fieldName -> {
      idxPartNameColumnsMap.put(this.getDbColumnMap().get(fieldName), Lists.newArrayList(this.getDbColumnMap().get(fieldName)));
    });
    List<String> createIndexStatementList = Lists.newArrayList();
    String idxSchemaPart = String0.notNull2empty2(Strings.nullToEmpty(this.getJavaTable().schema()), MessageFormat.format("`{0}`.", this.getJavaTable().schema()));
    idxPartNameColumnsMap.forEach((idxPartName, columnList) -> {
      String indexColumns = "`" + (columnList.size() > 1 ? Joiner.on("` asc, `").join(columnList) : columnList.get(0)) + "` asc";
      createIndexStatementList.add(MessageFormat.format("{0} {1}`{2}` {3} `{4}` ({5});", Keyword0.ALTER_TABLE, idxSchemaPart, this.getDbTableName(), Keyword0.ADD_UNIQUE_INDEX, UNIQUE_INDEX_NAME_PREFIX + idxPartName, indexColumns));
    });
    return Joiner.on(String0.BR).join(createIndexStatementList);
  }

  public String createTableSql() {
    List<String> sqlList = Lists.newArrayList();
    String idxSchemaPart = String0.notNull2empty2(Strings.nullToEmpty(this.getJavaTable().schema()), MessageFormat.format("`{0}`.", this.getJavaTable().schema()));
    sqlList.add(MessageFormat.format("{0} {1}`{2}` (", Keyword0.CREATE_TABLE, idxSchemaPart, this.getDbTableName()));
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
    sqlList.add(MessageFormat.format("  {0} (`{1}`)", Keyword0.PRIMARY_KEY, Joiner.on("`,`").join(this.getIdFieldNameList().stream().map(idFieldName -> this.getDbColumnMap().get(idFieldName)).collect(Collectors.toList()))));
    sqlList.add(String0.CLOSE_PARENTHESIS + String0.SEMICOLON);
    return Joiner.on("\n").join(sqlList);
  }

  public List<OperationContent> findWhereOCs(@NonNull String fieldName) {
    List<OperationContent> rtnList = Lists.newArrayList();
    //implements by sub entity
    return rtnList;
  }

  public String fullTableName() {
    return String0.notNull2empty2(Strings.nullToEmpty(this.getJavaTable().schema()), this.getJavaTable().schema() + String0.DOT) + this.getDbTableName();
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
    if (this.getJavaTable() == null) {
      this.setJavaTable(this.getClass().getAnnotation(Table.class));
    }
    if (Strings.isNullOrEmpty(this.getJavaTable().name())) {
      String classTableName = String0.upper2lower(Lists.reverse(Lists.newArrayList(this.getClass().getName().split(String20.BACKSLASH_DOT))).get(0));
      this.setDbTableName("t" + (classTableName.startsWith(String0.UNDERLINE) ? classTableName : String0.UNDERLINE + classTableName));
    } else {
      this.setDbTableName(this.getJavaTable().name());
    }
  }

  public void mapRow(ResultSet rs) {
    String columnFieldTypeString;
    Object o;
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
  public Tuple.Pair<List<String>, List<Object>> appendVersion2ByIdSql(@NonNull Tuple.Pair<List<String>, List<Object>> byIdSqlTuple) {
    List<String> sqlList = Tuple.getFirst(byIdSqlTuple);
    List<Object> rtnObjectList = Tuple.getSecond(byIdSqlTuple);

    List<String> whereVersionList = Lists.newArrayList();
    whereStatement(whereVersionList, rtnObjectList, this.getVersionFieldNameList());
    whereStatementExt(whereVersionList, rtnObjectList, this.getVersionFieldNameList());
    if (whereVersionList.size() > 0) {
      if (sqlList.contains(Keyword0.WHERE)) {
        sqlList.add(Keyword0.AND);
      } else {
        sqlList.add(Keyword0.WHERE);
      }
      sqlList.addAll(whereVersionList);
    }
    return Tuple.of(sqlList, rtnObjectList);
  }

  public Tuple.Pair<List<String>, List<Object>> appendWhereByFields2Sql(@NonNull List<String> sqlList, @NonNull List<Object> rtnObjectList, @NonNull List<String> fieldNameList) {
    List<String> whereList = Lists.newArrayList();
    whereStatement(whereList, rtnObjectList, fieldNameList);
    whereStatementExt(whereList, rtnObjectList, fieldNameList);

    if (whereList.size() > 0) {
      if (sqlList.contains(Keyword0.WHERE)) {
        sqlList.add(Keyword0.AND);
      } else {
        sqlList.add(Keyword0.WHERE);
      }
      sqlList.add(Joiner.on(Keyword0.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX).join(whereList));
    }

    return Tuple.of(sqlList, rtnObjectList);
  }

  public Tuple.Pair<String, List<Object>> deleteByIdSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.appendWhereByFields2Sql(Lists.newArrayList(Keyword0.DELETE_FROM, this.fullTableName()), Lists.newArrayList(), this.getIdFieldNameList());
    return Tuple.of(Joiner.on(String0.BLACK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> deleteByIdAndVersionSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.appendVersion2ByIdSql(this.appendWhereByFields2Sql(Lists.newArrayList(Keyword0.DELETE_FROM, this.fullTableName()), Lists.newArrayList(), this.getIdFieldNameList()));
    return Tuple.of(Joiner.on(String0.BLACK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  /**
   * Less attentions, maybe want more limit?
   */
  public Tuple.Pair<String, List<Object>> deleteSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.appendWhereByFields2Sql(Lists.newArrayList(Keyword0.DELETE_FROM, this.fullTableName()), Lists.newArrayList(), this.getFieldNameList());
    return Tuple.of(Joiner.on(String0.BLACK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> insertSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> insertList = Lists.newArrayList();
    insertStatement(insertList, rtnObjectList);
    insertStatementExt(insertList, rtnObjectList);

    List<String> sqlList = Lists.newArrayList();
    sqlList.add(Keyword0.INSERT_INFO);
    sqlList.add(this.fullTableName());
    sqlList.add(String0.OPEN_PARENTHESIS + Joiner.on(String0.COMMA).join(insertList) + String0.CLOSE_PARENTHESIS);
    sqlList.add(Keyword0.VALUES);
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

  public Tuple.Pair<String, List<Object>> selectCountSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(Lists.newArrayList(Keyword0.COUNT_1_), Lists.newArrayList());
    return Tuple.of(Joiner.on(String0.BLACK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> selectSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> selectList = Lists.newArrayList();
    selectStatement(selectList, rtnObjectList);
    selectStatementExt(selectList, rtnObjectList);

    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(selectList, rtnObjectList);
    return Tuple.of(Joiner.on(String0.BLACK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<String> selectList, @NonNull List<Object> selectObjectList) {
    List<Object> rtnObjectList = Lists.newArrayList();
    rtnObjectList.addAll(selectObjectList);

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

  public Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<Object> objectList, @NonNull List<String> selectList, @NonNull List<String> fromList, List<String> whereList, List<String> groupByList, List<String> havingList, List<String> orderByList) {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> sqlList = Lists.newArrayList();
    sqlList.add(Keyword0.SELECT);
    sqlList.add(Joiner.on(String0.COMMA).join(selectList));
    sqlList.add(Keyword0.FROM);
    sqlList.add(Joiner.on(String0.COMMA).join(fromList));
    if (whereList != null && whereList.size() > 0) {
      sqlList.add(Keyword0.WHERE);
      sqlList.add(Joiner.on(Keyword0.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX).join(whereList));
    }
    if (groupByList != null && groupByList.size() > 0) {
      sqlList.add(Keyword0.GROUP_BY);
      sqlList.add(Joiner.on(String0.COMMA).join(groupByList));
    }
    if (havingList != null && havingList.size() > 0) {
      sqlList.add(Keyword0.HAVING);
      sqlList.add(Joiner.on(Keyword0.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX).join(havingList));
    }
    if (orderByList != null && orderByList.size() > 0) {
      sqlList.add(Keyword0.ORDER_BY);
      sqlList.add(Joiner.on(String0.COMMA).join(orderByList));
    }
    rtnObjectList.addAll(objectList);

    List<String> limitAndOffsetList = Lists.newArrayList();
    limitAndOffsetStatement(limitAndOffsetList, rtnObjectList);
    limitAndOffsetStatementExt(limitAndOffsetList, rtnObjectList);
    if (limitAndOffsetList.size() > 0) {
      sqlList.add(Joiner.on(String0.BLACK).join(limitAndOffsetList));
    }

    return Tuple.of(sqlList, rtnObjectList);
  }

  public void selectStatement(@NonNull List<String> selectList, @NonNull List<Object> objectList) {
    selectList.addAll(this.getFieldNameList().stream().map((String fieldName) -> this.getDbColumnMap().get(fieldName)).collect(Collectors.toList()));
  }

  public void selectStatementExt(@NonNull List<String> selectList, @NonNull List<Object> objectList) {
    //implements by sub entity
  }

  public Tuple.Pair<String, List<Object>> updateByIdSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.updateByIdSqlList();
    return Tuple.of(Joiner.on(String0.BLACK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  private Tuple.Pair<List<String>, List<Object>> updateByIdSqlList() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> updateList = Lists.newArrayList();
    updateStatement(updateList, rtnObjectList);
    updateStatementExt(updateList, rtnObjectList);

    List<String> whereIdList = Lists.newArrayList();
    whereStatement(whereIdList, rtnObjectList, this.getIdFieldNameList());
    whereStatementExt(whereIdList, rtnObjectList, this.getIdFieldNameList());

    List<String> sqlList = Lists.newArrayList();
    sqlList.add(Keyword0.UPDATE);
    sqlList.add(this.fullTableName());
    sqlList.add(Keyword0.SET);
    sqlList.add(Joiner.on(String0.COMMA).join(updateList));
    if (whereIdList.size() > 0) {
      sqlList.add(Keyword0.WHERE);
      sqlList.add(Joiner.on(Keyword0.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX).join(whereIdList));
    }

    return Tuple.of(sqlList, rtnObjectList);
  }

  public Tuple.Pair<String, List<Object>> updateByIdAndVersionSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.appendVersion2ByIdSql(this.updateByIdSqlList());
    return Tuple.of(Joiner.on(String0.BLACK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
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
    fromList.add(this.fullTableName());
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
    PageHelper pageHelper = this.getPageHelper() == null ? new PageHelper() : this.getPageHelper();
    limitAndOffsetList.add(MessageFormat.format("{0} {1}", Keyword0.LIMIT, Integer0.gt2d(Integer0.null2Default(pageHelper.getLimit(), PageHelper.DEFAULT_LIMIT), PageHelper.DEFAULT_LIMIT)));
    limitAndOffsetList.add(MessageFormat.format("{0} {1}", Keyword0.OFFSET, Integer0.lt2d(Integer0.null2Zero(pageHelper.getOffset()), 0)));
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
        for (OperationContent oc : this.findWhereOCs(fieldName)) {
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
            objectList.add(Strings.nullToEmpty(oc.getBw()) + oc.getCs() + Strings.nullToEmpty(oc.getEw()));
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
