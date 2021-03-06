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
import org.shaneking.skava.lang.Integer0;
import org.shaneking.skava.lang.String0;
import org.shaneking.skava.lang.String20;
import org.shaneking.skava.persistence.Tuple;
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
public abstract class SKEntity<J> {
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
  private final List<String> idFieldNameList = Lists.newArrayList();
  @Getter
  @JsonIgnore
  @Transient
  private final List<String> verFieldNameList = Lists.newArrayList();
  @Getter
  @JsonIgnore
  @Setter
  @Transient
  private String dbTableName;
  @Getter
  @JsonIgnore
  @Setter
  @Transient
  private Table javaTable;

  @Getter
  @Setter
  @Transient
  private List<String> groupByList;
  @Getter
  @Setter
  @Transient
  private J havingOCs;
  @Getter
  @Setter
  @Transient
  private List<String> orderByList;
  @Getter
  @Setter
  @Transient
  private PageHelper pageHelper;
  @Getter
  @Setter
  @Transient
  private List<String> selectList;
  /**
   * I don't want Map<String, OperationContent> to limit everyone, J maybe Map/fastjson/gson/jackson...
   * <blockquote><pre>
   *     {
   *         crtDateTime:{
   *             op:'between',
   *             c:['2017-09-10','2019-04-27']
   *         },
   *         delDateTime:{
   *             op:'>',
   *             c:'2017-09-10'
   *         },
   *         modDateTime:{
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

  //init
  public SKEntity() {
    initTableInfo();
    initColumnInfo(this.getClass());
  }

  public void initTableInfo() {
    if (this.getJavaTable() == null) {
      this.setJavaTable(this.getClass().getAnnotation(Table.class));
    }
    if (Strings.isNullOrEmpty(this.getJavaTable().name())) {
      String classTableName = String0.field2DbColumn(Lists.reverse(Lists.newArrayList(this.getClass().getName().split(String20.BACKSLASH_DOT))).get(0));
      this.setDbTableName("t" + (classTableName.startsWith(String0.UNDERLINE) ? classTableName : String0.UNDERLINE + classTableName));
    } else {
      this.setDbTableName(this.getJavaTable().name());
    }
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
          this.getDbColumnMap().put(field.getName(), Strings.isNullOrEmpty(column.name()) ? String0.field2DbColumn(field.getName()) : column.name());
          if (this.getFieldNameList().indexOf(field.getName()) == -1) {
            this.getFieldNameList().add(field.getName());
          }
        }
        if (field.getAnnotation(Id.class) != null && this.getIdFieldNameList().indexOf(field.getName()) == -1) {
          this.getIdFieldNameList().add(field.getName());
        }
        if (field.getAnnotation(Version.class) != null && this.getVerFieldNameList().indexOf(field.getName()) == -1) {
          this.getVerFieldNameList().add(field.getName());
        }
      }
    }
  }

  //create table
  public String createTableIfNotExistSql() {
    String idxSql = createTableIndexSql();
    idxSql = String0.isNull2Empty(idxSql) ? String0.EMPTY : (idxSql + String0.BR_LINUX + String0.BR_LINUX);
    return "drop procedure if exists p_" + this.getDbTableName() + "_create;" + String0.BR_LINUX +
      "delimiter $$" + String0.BR_LINUX +
      "create procedure p_" + this.getDbTableName() + "_create() begin" + String0.BR_LINUX +
      "if not exists (select * from information_schema.tables where table_schema = '" + this.getJavaTable().schema() + "' and table_name = '" + this.getDbTableName() + "')" + String0.BR_LINUX +
      "then" + String0.BR_LINUX + String0.BR_LINUX +
      createTableSql() + String0.BR_LINUX + String0.BR_LINUX +
      idxSql +
      "end if;" + String0.BR_LINUX +
      "end;" + String0.BR_LINUX +
      "$$" + String0.BR_LINUX +
      "delimiter ;" + String0.BR_LINUX +
      "call p_" + this.getDbTableName() + "_create();" + String0.BR_LINUX +
      "drop procedure if exists p_" + this.getDbTableName() + "_create;" + String0.BR_LINUX;
  }

  public String createTableSql() {
    List<String> sqlList = Lists.newArrayList();
    String idxSchemaPart = String0.notNull2empty2(Strings.nullToEmpty(this.getJavaTable().schema()), MessageFormat.format("`{0}`.", this.getJavaTable().schema()));
    sqlList.add(MessageFormat.format("{0} {1}`{2}` (", Keyword0.CREATE_TABLE, idxSchemaPart, this.getDbTableName()));
    for (String versionColumn : this.getVerFieldNameList()) {
      sqlList.add(this.createColumnStatement(versionColumn, true));
    }
    for (String idColumn : this.getIdFieldNameList()) {
      sqlList.add(this.createColumnStatement(idColumn, true));
    }
    for (String columnName : this.getFieldNameList()) {
      if (this.getIdFieldNameList().indexOf(columnName) == -1 && this.getVerFieldNameList().indexOf(columnName) == -1) {
        sqlList.add(this.createColumnStatement(columnName, false));
      }
    }
    sqlList.add(MessageFormat.format("  {0} (`{1}`)", Keyword0.PRIMARY_KEY, Joiner.on("`,`").join(this.getIdFieldNameList().stream().map(idFieldName -> this.getDbColumnMap().get(idFieldName)).collect(Collectors.toList()))));
    sqlList.add(String0.CLOSE_PARENTHESIS + String0.SEMICOLON);
    return Joiner.on("\n").join(sqlList);
  }

  public String createTableIndexSql() {
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

  //abstracts
  @NonNull
  public abstract List<OperationContent> findHavingOCs(@NonNull String fieldName);

  @NonNull
  public abstract List<OperationContent> findWhereOCs(@NonNull String fieldName);

  //prepares
  public void fillOc(@NonNull List<String> list, @NonNull List<Object> objectList, OperationContent oc, String leftExpr) {
    if (Keyword0.BETWEEN.equalsIgnoreCase(oc.getOp())) {
      if (oc.getCl() != null && oc.getCl().size() == 2) {
        list.add(leftExpr + String0.BLANK + oc.getOp() + String0.BLANK + String0.QUESTION + String0.BLANK + Keyword0.AND + String0.BLANK + String0.QUESTION);
        objectList.addAll(oc.getCl());
      }
    } else if (Keyword0.IN.equalsIgnoreCase(oc.getOp())) {
      if (oc.getCl() != null && oc.getCl().size() > 0) {
        list.add(leftExpr + String0.BLANK + oc.getOp() + String0.BLANK + String0.OPEN_PARENTHESIS + Joiner.on(String0.COMMA).join(Collections.nCopies(oc.getCl().size(), String0.QUESTION)) + String0.CLOSE_PARENTHESIS);
        objectList.addAll(oc.getCl());
      }
    } else {
      list.add(leftExpr + String0.BLANK + oc.getOp() + String0.BLANK + String0.QUESTION);
      objectList.add(Strings.nullToEmpty(oc.getBw()) + oc.getCs() + Strings.nullToEmpty(oc.getEw()));
    }
  }

  public String fullTableName() {
    return String0.notNull2empty2(Strings.nullToEmpty(this.getJavaTable().schema()), this.getJavaTable().schema() + String0.DOT) + this.getDbTableName();
  }

  public List<String> lstSelectFiled() {
    return this.getSelectList() != null && this.getSelectList().size() > 0 ? this.getSelectList() : this.getFieldNameList();
  }

  public void mapRow(ResultSet rs) {
    String columnFieldTypeString;
    Object o;
    for (String fieldName : this.lstSelectFiled()) {
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

  //crud
  public Tuple.Pair<String, List<Object>> deleteSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> sqlList = Lists.newArrayList();
    sqlList.add(Keyword0.DELETE_FROM);
    sqlList.add(this.fullTableName());

    List<String> whereList = Lists.newArrayList();
    whereStatement(whereList, rtnObjectList);
    if (whereList.size() > 0) {
      sqlList.add(Keyword0.WHERE);
      sqlList.add(Joiner.on(Keyword0.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX).join(whereList));
    }
    return Tuple.of(Joiner.on(String0.BLANK).join(sqlList), rtnObjectList);
  }

  public Tuple.Pair<String, List<Object>> insertSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> insertList = Lists.newArrayList();
    insertStatement(insertList, rtnObjectList);

    List<String> sqlList = Lists.newArrayList();
    sqlList.add(Keyword0.INSERT_INFO);
    sqlList.add(this.fullTableName());
    sqlList.add(String0.OPEN_PARENTHESIS + Joiner.on(String0.COMMA).join(insertList) + String0.CLOSE_PARENTHESIS);
    sqlList.add(Keyword0.VALUES);
    sqlList.add(String0.OPEN_PARENTHESIS + Strings.repeat(String0.COMMA + String0.QUESTION, insertList.size()).substring(1) + String0.CLOSE_PARENTHESIS);

    return Tuple.of(Joiner.on(String0.BLANK).join(sqlList), rtnObjectList);
  }

  public void insertStatement(@NonNull List<String> insertList, @NonNull List<Object> objectList) {
    Object o;
    for (String fieldName : this.getFieldNameList()) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.error(e.toString());
      }
      if (!String0.isNullOrEmpty(String.valueOf(o))) {
        insertList.add(this.getDbColumnMap().get(fieldName));
        objectList.add(o);
      }
    }
  }

  public Tuple.Pair<String, List<Object>> selectCountSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(Lists.newArrayList(Keyword0.COUNT_1_), Lists.newArrayList());
    return Tuple.of(Joiner.on(String0.BLANK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> selectIdsSql() {
    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(Lists.newArrayList(Keyword0.GROUP__CONCAT_ID_), Lists.newArrayList());
    return Tuple.of(Joiner.on(String0.BLANK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<String, List<Object>> selectSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> selectList = Lists.newArrayList();
    selectStatement(selectList, rtnObjectList);

    Tuple.Pair<List<String>, List<Object>> pair = this.selectSql(selectList, rtnObjectList);
    return Tuple.of(Joiner.on(String0.BLANK).join(Tuple.getFirst(pair)), Tuple.getSecond(pair));
  }

  public Tuple.Pair<List<String>, List<Object>> selectSql(@NonNull List<String> selectList, @NonNull List<Object> selectObjectList) {
    List<Object> rtnObjectList = Lists.newArrayList();
    rtnObjectList.addAll(selectObjectList);

    List<String> fromList = Lists.newArrayList();
    fromStatement(fromList, rtnObjectList);

    List<String> whereList = Lists.newArrayList();
    whereStatement(whereList, rtnObjectList);

    List<String> groupByList = Lists.newArrayList();
    groupByStatement(groupByList, rtnObjectList);

    List<String> havingList = Lists.newArrayList();
    havingStatement(havingList, rtnObjectList);

    List<String> orderByList = Lists.newArrayList();
    orderByStatement(orderByList, rtnObjectList);

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
    if (limitAndOffsetList.size() > 0) {
      sqlList.add(Joiner.on(String0.BLANK).join(limitAndOffsetList));
    }
    return Tuple.of(sqlList, rtnObjectList);
  }

  public void selectStatement(@NonNull List<String> selectList, @NonNull List<Object> objectList) {
    //count(1)
    selectList.addAll(this.lstSelectFiled().stream().map((String fieldName) -> String0.nullOrEmptyTo(this.getDbColumnMap().get(fieldName), fieldName)).collect(Collectors.toList()));
  }

  public Tuple.Pair<String, List<Object>> updateSql() {
    List<Object> rtnObjectList = Lists.newArrayList();

    List<String> updateList = Lists.newArrayList();
    updateStatement(updateList, rtnObjectList);

    List<String> whereList = Lists.newArrayList();
    whereEntityStatement(whereList, rtnObjectList, this.getIdFieldNameList());
    whereEntityStatement(whereList, rtnObjectList, this.getVerFieldNameList());
    whereOCsStatement(whereList, rtnObjectList, this.getFieldNameList());

    List<String> sqlList = Lists.newArrayList();
    sqlList.add(Keyword0.UPDATE);
    sqlList.add(this.fullTableName());
    sqlList.add(Keyword0.SET);
    sqlList.add(Joiner.on(String0.COMMA).join(updateList));
    if (whereList.size() > 0) {
      sqlList.add(Keyword0.WHERE);
      sqlList.add(Joiner.on(Keyword0.AND_WITH_BLACK_PREFIX_WITH_BLACK_SUFFIX).join(whereList));
    }
    return Tuple.of(Joiner.on(String0.BLANK).join(sqlList), rtnObjectList);
  }

  public void updateStatement(@NonNull List<String> updateList, @NonNull List<Object> objectList) {
    Object o = null;
    for (String fieldName : this.getFieldNameList().stream().filter(fieldName -> this.getIdFieldNameList().indexOf(fieldName) == -1 && this.getColumnMap().get(fieldName).updatable()).collect(Collectors.toList())) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.error(e.toString());
      }
      if (o != null) {//can update to null
        updateList.add(this.getDbColumnMap().get(fieldName) + String20.EQUAL_QUESTION);
        if (this.getVerFieldNameList().indexOf(fieldName) == -1) {
          objectList.add(o);
        } else {
          objectList.add((Integer) o + 1);
        }
      }
    }
  }

  //other statements
  public void fromStatement(@NonNull List<String> fromList, @NonNull List<Object> objectList) {
    fromList.add(this.fullTableName());
  }

  public void groupByStatement(@NonNull List<String> groupByList, @NonNull List<Object> objectList) {
    if (this.getGroupByList() != null) {
      groupByList.addAll(this.getGroupByList());
    }
  }

  public void havingStatement(@NonNull List<String> havingList, @NonNull List<Object> objectList) {
    if (this.getHavingOCs() != null) {
      this.havingStatement(havingList, objectList, this.getFieldNameList());
    }
  }

  public void havingStatement(@NonNull List<String> havingList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    for (String fieldName : fieldNameList) {
      if (this.getColumnMap().get(fieldName) != null) {
        for (OperationContent oc : this.findHavingOCs(fieldName)) {
          this.fillOc(havingList, objectList, oc, String0.null2empty2(oc.getLe(), this.getDbColumnMap().get(fieldName)));
        }
      }
    }
  }

  public void limitAndOffsetStatement(@NonNull List<String> limitAndOffsetList, @NonNull List<Object> objectList) {
    PageHelper pageHelper = this.getPageHelper() == null ? new PageHelper() : this.getPageHelper();
    limitAndOffsetList.add(MessageFormat.format("{0} {1}", Keyword0.LIMIT, String.valueOf(Integer0.gt2d(Integer0.null2Default(pageHelper.getLimit(), PageHelper.DEFAULT_LIMIT), PageHelper.MAX_LIMIT))));//add String.valueOf to fix 1000+ to 1,000+
    limitAndOffsetList.add(MessageFormat.format("{0} {1}", Keyword0.OFFSET, String.valueOf(Integer0.lt2d(Integer0.null2Zero(pageHelper.getOffset()), 0))));
  }

  public void orderByStatement(@NonNull List<String> orderByList, @NonNull List<Object> objectList) {
    if (this.getOrderByList() != null) {
      orderByList.addAll(this.getOrderByList());
    }
  }

  public void whereEntityStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    Object o = null;
    for (String fieldName : fieldNameList) {
      try {
        o = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)).invoke(this);
      } catch (Exception e) {
        o = null;
        log.error(e.toString());
      }
      if (this.getColumnMap().get(fieldName) != null) {
        if (!String0.isNullOrEmpty(String.valueOf(o))) {//whereOCs support empty
          whereList.add(this.getDbColumnMap().get(fieldName) + String20.EQUAL_QUESTION);
          objectList.add(o);
        }
      }
    }
  }

  public void whereOCsStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList, @NonNull List<String> fieldNameList) {
    for (String fieldName : fieldNameList) {
      for (OperationContent oc : this.findWhereOCs(fieldName)) {
        this.fillOc(whereList, objectList, oc, String0.null2empty2(oc.getLe(), this.getDbColumnMap().get(fieldName)));
      }
    }
  }

  public void whereStatement(@NonNull List<String> whereList, @NonNull List<Object> objectList) {
    this.whereEntityStatement(whereList, objectList, this.getFieldNameList());
    this.whereOCsStatement(whereList, objectList, this.getFieldNameList());
  }
}
