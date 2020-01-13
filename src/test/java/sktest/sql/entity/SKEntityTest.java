package sktest.sql.entity;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.shaneking.jackson.databind.OM3;
import org.shaneking.skava.lang.String0;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.PrepareSKEntity;

import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

//Parameterized step1:add Parameterized.class to RunWith
@RunWith(Parameterized.class)
@Slf4j
public class SKEntityTest extends SKUnit {
  public static final String SKTEST1_ID = "sktest1_id";
  private static final String FMT_FILE = "src/test/java/sktest/sql/entity/prepare/PrepareSKEntity_{0}.sql";
  private PrepareSKEntity prepareEntity;
  private File sqlFile;

  //Parameterized step2:use step2 data to constructor object
  public SKEntityTest(String invalid, String id, Integer version, String hasLength, String noGetMethod, String withoutAnnotation, String reName) {
    super();
    prepareEntity = new PrepareSKEntity().setInvalid(invalid).setId(id).setVer(version).setHasLength(hasLength).setNoGetMethod(noGetMethod).setWithoutAnnotation(withoutAnnotation).setReName(reName);
  }

  //Parameterized step2:static method return collection
  @Parameterized.Parameters
  public static List<Object[]> SKEntityTestParameters() {
    return Arrays.asList(new Object[][]{{null, null, null, null, null, null, null}, {String0.Y, SKTEST1_ID, 1, "hasLength", "noGetMethod", "withoutAnnotation", "reName"}});
  }

//  public static void main(String[] args) {
//    System.out.println(OM3.writeValueAsString(new PrepareSKEntity()));
//  }

  @Override
  public void setUp() {
    super.setUp();
    sqlFile = new File(MessageFormat.format(FMT_FILE, testName.getMethodName()));
//    if (!sqlFile.exists()) {
//      try {
//        sqlFile.createNewFile();
//      } catch (Exception e) {
//        log.error(e.getMessage(), e);
//      }
//    }
  }

  @Test
  public void createIndexSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), prepareEntity.createIndexSql().trim());
  }

  @Test
  public void createTableSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), prepareEntity.createTableSql().trim());
  }

  @Test
  public void findWhereOCs() {
    Assert.assertEquals("[]", OM3.writeValueAsString(prepareEntity.findWhereOCs("id")));
  }

  @Test
  public void fullTableName() {
    Assert.assertEquals("sktest1_schema.sktest1_table", prepareEntity.fullTableName());
  }

  @Test
  public void deleteSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.deleteSql()));
  }

  @Test
  public void insertSql() throws Exception {
    if (!"{}".equals(OM3.writeValueAsString(prepareEntity))) {
      Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.insertSql()));
    }
  }

  @Test(expected = StringIndexOutOfBoundsException.class)
  public void insertSql_null() throws Exception {
    prepareEntity = new PrepareSKEntity();
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.insertSql()));
  }

  @Test
  public void selectCountSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.selectCountSql()));
  }

  @Test
  public void selectSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.selectSql()));
  }

  @Test
  public void selectSql_a2() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.selectSql(Lists.newArrayList(), Lists.newArrayList())));
  }

  @Test
  public void selectSql_a7() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.selectSql(Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList())));
  }

  @Test
  public void updateSql() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.updateSql()));
  }

  @Test
  public void updateSql_null() throws Exception {
    prepareEntity.setId(null);
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.updateSql()));
  }
}
