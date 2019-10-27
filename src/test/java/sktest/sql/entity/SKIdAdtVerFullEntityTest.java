package sktest.sql.entity;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.shaneking.skava.ling.lang.String0;
import org.shaneking.skava.t3.jackson.OM3;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.PrepareSKIdAdtVerFullEntity;

import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

//Parameterized step1:add Parameterized.class to RunWith
@RunWith(Parameterized.class)
@Slf4j
public class SKIdAdtVerFullEntityTest extends SKUnit {
  private static final String FMT_FILE = "src/test/java/sktest/sql/entity/prepare/PrepareSKIdAdtVerFullEntity_{0}.sql";
  private PrepareSKIdAdtVerFullEntity prepareEntity;
  private File sqlFile;

  //Parameterized step2:use step2 data to constructor object
  public SKIdAdtVerFullEntityTest(String invalid) {
    super();
    prepareEntity = new PrepareSKIdAdtVerFullEntity();
    prepareEntity.setDeleted(invalid);
  }

  //Parameterized step2:static method return collection
  @Parameterized.Parameters
  public static List<Object[]> SKEntityTestParameters() {
    return Arrays.asList(new Object[][]{{null}, {String0.Y}});
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
  public void findWhereOCs() throws Exception {
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareEntity.findWhereOCs("crtUserId")));
  }

  @Test
  public void fullTableName() {
    Assert.assertEquals("t_prepare_s_k_id_adt_ver_full_entity", prepareEntity.fullTableName());
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
}
