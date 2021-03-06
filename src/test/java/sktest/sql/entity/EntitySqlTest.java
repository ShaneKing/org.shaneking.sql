package sktest.sql.entity;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.shaneking.jackson.databind.OM3;
import org.shaneking.skava.io.FTN;
import org.shaneking.skava.lang.String0;
import org.shaneking.sql.Keyword0;
import org.shaneking.sql.entity.SKIdAdtVerEntity;
import org.shaneking.test.SKUnit;
import sktest.sql.entity.prepare.PrepareSqlSKIdAdtVerEntity;

import java.util.Arrays;
import java.util.List;

//Parameterized step1:add Parameterized.class to RunWith
@RunWith(Parameterized.class)
@Slf4j
public class EntitySqlTest extends SKUnit {
  public static final String SKTEST1_ID = "sktest1_id";
  private PrepareSqlSKIdAdtVerEntity prepareSqlSKIdAdtVerEntity;

  //Parameterized step2:use step2 data to constructor object
  public EntitySqlTest(String invalid, String id, Integer version, String hasLength, String noGetMethod, String withoutAnnotation, String reName, String longText) {
    super();
    prepareSqlSKIdAdtVerEntity = new PrepareSqlSKIdAdtVerEntity().setHasLength(hasLength).setNoGetMethod(noGetMethod).setWithoutAnnotation(withoutAnnotation).setReName(reName).setLongText(longText);
    prepareSqlSKIdAdtVerEntity.setVer(version).setInvalid(invalid).setId(id);
  }

  //Parameterized step2:static method return collection
  @Parameterized.Parameters
  public static List<Object[]> SKEntityTestParameters() {
    return Arrays.asList(new Object[][]{{null, null, null, null, null, null, null, null}
      , {String0.Y, SKTEST1_ID, 1, "hasLength", "noGetMethod", "withoutAnnotation", "reName", "longText"}});
  }

  @Test
  public void createTableIfNotExistSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), prepareSqlSKIdAdtVerEntity.createTableIfNotExistSql().trim());
  }

  @Test
  public void createTableSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), prepareSqlSKIdAdtVerEntity.createTableSql().trim());
  }

  @Test
  public void createTableIndexSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), prepareSqlSKIdAdtVerEntity.createTableIndexSql().trim());
  }

  @Test
  public void deleteSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.deleteSql()));
  }

  @Test
  public void findHavingOCs() throws Exception {
    if (testName.getMethodName().endsWith("[0]")) {
      prepareSqlSKIdAdtVerEntity.forceHavingOc(SKIdAdtVerEntity.FIELD__MOD_DATE_TIME).setOp(Keyword0.BETWEEN).setCl(Lists.newArrayList("2019-06-23 20:00:07", "2020-01-17 20:20:01"));
    } else if (testName.getMethodName().endsWith("[1]")) {
      prepareSqlSKIdAdtVerEntity.forceHavingOc(SKIdAdtVerEntity.FIELD__MOD_DATE_TIME).setOp(Keyword0.LIKE).setCs("2020-01-17").setEw("%");
    }
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.findHavingOCs(SKIdAdtVerEntity.FIELD__MOD_DATE_TIME)));
  }

  @Test
  public void findWhereOCs() throws Exception {
    if (testName.getMethodName().endsWith("[0]")) {
      prepareSqlSKIdAdtVerEntity.forceWhereOc(SKIdAdtVerEntity.FIELD__ID).setOp(Keyword0.IN).setCl(Lists.newArrayList(SKIdAdtVerEntity.FIELD__ID, SKTEST1_ID));
    } else if (testName.getMethodName().endsWith("[1]")) {
      prepareSqlSKIdAdtVerEntity.forceWhereOc(SKIdAdtVerEntity.FIELD__ID).setOp(String0.MORE).setCs("1579263862");
    }
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.findWhereOCs("id")));
  }

  @Test
  public void fullTableName() {
    Assert.assertEquals("sktest1_schema.sktest1_table", prepareSqlSKIdAdtVerEntity.fullTableName());
  }

  @Test
  public void insertSql() throws Exception {
    if (!"{}".equals(OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity))) {
      Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.insertSql()));
    }
  }

  @Test(expected = StringIndexOutOfBoundsException.class)
  public void insertSqlNull() throws Exception {
    prepareSqlSKIdAdtVerEntity = new PrepareSqlSKIdAdtVerEntity();
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.insertSql()));
  }

  @Test
  public void lstSelectFiled() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.lstSelectFiled()));
  }

  @Test
  public void selectCountSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.selectCountSql()));
  }

  @Test
  public void selectIdsSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.selectIdsSql()));
  }

  @Test
  public void selectSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.selectSql()));
  }

  @Test
  public void selectSqlA2() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.selectSql(Lists.newArrayList(), Lists.newArrayList())));
  }

  @Test
  public void selectSqlA7() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.selectSql(Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList())));
  }

  @Test
  public void updateSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.updateSql()));
  }

  @Test
  public void updateSqlNull() throws Exception {
    prepareSqlSKIdAdtVerEntity.setId(null);
    Assert.assertEquals(new String(Files.toByteArray(tstIFiles(FTN.SQL))).trim(), OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity.updateSql()));
  }
}
