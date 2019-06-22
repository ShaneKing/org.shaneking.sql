package sktest.sql.entity;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.PrepareSKAuditEntityColumnNoGetMethod;
import sktest.sql.entity.prepare.PrepareSKAuditEntityColumns;
import sktest.sql.entity.prepare.PrepareSKAuditEntityOverride;
import sktest.sql.entity.prepare.prepareSKAuditEntityTableName;

public class SKAuditEntityTest extends SKUnit {
  private PrepareSKAuditEntityColumnNoGetMethod prepareSKAuditEntityColumnNoGetMethod = new PrepareSKAuditEntityColumnNoGetMethod();
  private PrepareSKAuditEntityColumns prepareSKAuditEntityColumns = new PrepareSKAuditEntityColumns();
  private PrepareSKAuditEntityOverride prepareSKAuditEntityOverride = new PrepareSKAuditEntityOverride();
  private prepareSKAuditEntityTableName prepareSKAuditEntityTableName = new prepareSKAuditEntityTableName();

  @Before
  public void setUp() {
    super.setUp();
    prepareSKAuditEntityColumnNoGetMethod = new PrepareSKAuditEntityColumnNoGetMethod();
    prepareSKAuditEntityColumns = new PrepareSKAuditEntityColumns();
    prepareSKAuditEntityOverride = new PrepareSKAuditEntityOverride();
    prepareSKAuditEntityTableName = new prepareSKAuditEntityTableName();
  }

  @Test
  public void setter() {
    prepareSKAuditEntityColumnNoGetMethod.setNoGetMethod("").setCreateDatetime("").setCreateUserId("").setInvalid("").setInvalidDatetime("").setInvalidUserId("").setLastModifyDatetime("").setLastModifyUserId("").setWhereOCs("");
  }


  @Test
  public void initColumnInfo() {
    prepareSKAuditEntityColumns.initColumnInfo(prepareSKAuditEntityColumns.getClass());
    prepareSKAuditEntityTableName.initColumnInfo(prepareSKAuditEntityTableName.getClass());
  }

  @Test
  public void initTableInfo() {
    prepareSKAuditEntityColumns.initTableInfo();
    prepareSKAuditEntityTableName.initTableInfo();
  }

  @Test
  public void insertSql() {
    Assert.assertEquals(prepareSKAuditEntityColumns.insertSql().toString(), "[insert into testschema.t_prepare_s_k_audit_entity_columns (version) values (?),[1]]");
  }

  @Test
  public void insertStatement() {
    prepareSKAuditEntityColumns.insertStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test
  public void insertStatementColumnNoGetMethod() {
    prepareSKAuditEntityColumnNoGetMethod.insertStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void insertStatementNull1() {
    prepareSKAuditEntityColumns.insertStatement(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void insertStatementNull2() {
    prepareSKAuditEntityColumns.insertStatement(Lists.newArrayList(), null);
  }

  @Test
  public void insertStatementExt() {
    prepareSKAuditEntityColumns.insertStatementExt(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void insertStatementExtNull1() {
    prepareSKAuditEntityColumns.insertStatementExt(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void insertStatementExtNull2() {
    prepareSKAuditEntityColumns.insertStatementExt(Lists.newArrayList(), null);
  }

  @Test
  public void selectSql() {
    Assert.assertEquals(prepareSKAuditEntityColumns.selectSql().toString(), "[select create_datetime,create_user_id,invalid,invalid_datetime,invalid_user_id,last_modify_datetime,last_modify_user_id,id,version,has_length,re_name from testschema.t_prepare_s_k_audit_entity_columns where version=? limit 100,[1]]");
  }

  @Test
  public void selectSqlColumnNull() {
    prepareSKAuditEntityColumns.setVersion(null);
    Assert.assertEquals(prepareSKAuditEntityColumns.selectSql().toString(), "[select create_datetime,create_user_id,invalid,invalid_datetime,invalid_user_id,last_modify_datetime,last_modify_user_id,id,version,has_length,re_name from testschema.t_prepare_s_k_audit_entity_columns limit 100,[]]");
  }

  @Test
  public void selectSqlOverride() {
    Assert.assertEquals(prepareSKAuditEntityOverride.selectSql().toString(), "[select create_datetime,create_user_id,invalid,invalid_datetime,invalid_user_id,last_modify_datetime,last_modify_user_id,id,version from t_prepare_s_k_audit_entity_override where create_user_id in (?,?,?) and invalid_datetime between ? and ? and last_modify_datetime like ? and version=? group by version having version > ? order by version limit 100,[1, a, ,, 1949-10-01, 1996-07, %1949-10-01%, 1, 1]]");
  }

  @Test
  public void selectStatement() {
    prepareSKAuditEntityColumns.selectStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void selectStatementNull1() {
    prepareSKAuditEntityColumns.selectStatement(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void selectStatementNull2() {
    prepareSKAuditEntityColumns.selectStatement(Lists.newArrayList(), null);
  }

  @Test
  public void selectStatementExt() {
    prepareSKAuditEntityColumns.selectStatementExt(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void selectStatementExtNull1() {
    prepareSKAuditEntityColumns.selectStatementExt(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void selectStatementExtNull2() {
    prepareSKAuditEntityColumns.selectStatementExt(Lists.newArrayList(), null);
  }

  @Test
  public void updateByIdAndVersionSql() {
    Assert.assertEquals(prepareSKAuditEntityColumns.updateByIdAndVersionSql().toString(), "[update testschema.t_prepare_s_k_audit_entity_columns set version=? where version=?,[2, 1]]");
    prepareSKAuditEntityColumns.setVersion(null);
    Assert.assertEquals(prepareSKAuditEntityColumns.updateByIdAndVersionSql().toString(), "[update testschema.t_prepare_s_k_audit_entity_columns set ,[]]");//TODO, maybe control by user
  }

  @Test
  public void updateStatement() {
    prepareSKAuditEntityColumns.updateStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test
  public void updateStatementColumnNoGetMethod() {
    prepareSKAuditEntityColumnNoGetMethod.updateStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void updateStatementNull1() {
    prepareSKAuditEntityColumns.updateStatement(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void updateStatementNull2() {
    prepareSKAuditEntityColumns.updateStatement(Lists.newArrayList(), null);
  }

  @Test
  public void updateStatementExt() {
    prepareSKAuditEntityColumns.updateStatementExt(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void updateStatementExtNull1() {
    prepareSKAuditEntityColumns.updateStatementExt(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void updateStatementExtNull2() {
    prepareSKAuditEntityColumns.updateStatementExt(Lists.newArrayList(), null);
  }

  @Test
  public void fromStatement() {
    prepareSKAuditEntityColumns.fromStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void fromStatementNull1() {
    prepareSKAuditEntityColumns.fromStatement(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void fromStatementNull2() {
    prepareSKAuditEntityColumns.fromStatement(Lists.newArrayList(), null);
  }

  @Test
  public void fromStatementExt() {
    prepareSKAuditEntityColumns.fromStatementExt(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void fromStatementExtNull1() {
    prepareSKAuditEntityColumns.fromStatementExt(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void fromStatementExtNull2() {
    prepareSKAuditEntityColumns.fromStatementExt(Lists.newArrayList(), null);
  }

  @Test
  public void groupByStatement() {
    prepareSKAuditEntityColumns.groupByStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void groupByStatementNull1() {
    prepareSKAuditEntityColumns.groupByStatement(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void groupByStatementNull2() {
    prepareSKAuditEntityColumns.groupByStatement(Lists.newArrayList(), null);
  }

  @Test
  public void groupByStatementExt() {
    prepareSKAuditEntityColumns.groupByStatementExt(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void groupByStatementExtNull1() {
    prepareSKAuditEntityColumns.groupByStatementExt(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void groupByStatementExtNull2() {
    prepareSKAuditEntityColumns.groupByStatementExt(Lists.newArrayList(), null);
  }

  @Test
  public void havingStatement() {
    prepareSKAuditEntityColumns.havingStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void havingStatementNull1() {
    prepareSKAuditEntityColumns.havingStatement(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void havingStatementNull2() {
    prepareSKAuditEntityColumns.havingStatement(Lists.newArrayList(), null);
  }

  @Test
  public void havingStatementExt() {
    prepareSKAuditEntityColumns.havingStatementExt(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void havingStatementExtNull1() {
    prepareSKAuditEntityColumns.havingStatementExt(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void havingStatementExtNull2() {
    prepareSKAuditEntityColumns.havingStatementExt(Lists.newArrayList(), null);
  }

  @Test
  public void orderByStatement() {
    prepareSKAuditEntityColumns.orderByStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void orderByStatementNull1() {
    prepareSKAuditEntityColumns.orderByStatement(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void orderByStatementNull2() {
    prepareSKAuditEntityColumns.orderByStatement(Lists.newArrayList(), null);
  }

  @Test
  public void orderByStatementExt() {
    prepareSKAuditEntityColumns.orderByStatementExt(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void orderByStatementExtNull1() {
    prepareSKAuditEntityColumns.orderByStatementExt(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void orderByStatementExtNull2() {
    prepareSKAuditEntityColumns.orderByStatementExt(Lists.newArrayList(), null);
  }

  @Test
  public void whereStatement() {
    prepareSKAuditEntityColumnNoGetMethod.whereStatement(Lists.newArrayList(), Lists.newArrayList());
    prepareSKAuditEntityColumns.whereStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test
  public void whereStatementColumnMap() {
    prepareSKAuditEntityColumns.setHasLength("hasLength");
    prepareSKAuditEntityColumns.getColumnMap().remove("hasLength");
    prepareSKAuditEntityColumns.whereStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test
  public void whereStatementOperationContent() {
    prepareSKAuditEntityOverride.whereStatement(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void whereStatementNull1() {
    prepareSKAuditEntityColumns.whereStatement(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void whereStatementNull2() {
    prepareSKAuditEntityColumns.whereStatement(Lists.newArrayList(), null);
  }

  @Test
  public void whereStatementExt() {
    prepareSKAuditEntityColumns.whereStatementExt(Lists.newArrayList(), Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void whereStatementExtNull1() {
    prepareSKAuditEntityColumns.whereStatementExt(null, Lists.newArrayList());
  }

  @Test(expected = NullPointerException.class)
  public void whereStatementExtNull2() {
    prepareSKAuditEntityColumns.whereStatementExt(Lists.newArrayList(), null);
  }
}
