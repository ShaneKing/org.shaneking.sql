package sktest.sql;

import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shaneking.jackson.databind.OM3;
import org.shaneking.skava.lang.Integer0;
import org.shaneking.sql.PageHelper;
import sktest.sql.prepare.PrepareSKEntityPageHelper;

import java.io.File;
import java.text.MessageFormat;

public class PageHelperTest extends SKUnit {
  public static final String FMT_FILE = "src/test/java/sktest/sql/prepare/PrepareSKEntityPageHelper_{0}.sql";
  private PrepareSKEntityPageHelper prepareSKEntityPageHelper;
  private File sqlFile;

  @Before
  public void setUp() {
    super.setUp();
    prepareSKEntityPageHelper = new PrepareSKEntityPageHelper();
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
  public void newInstance() {
    Assert.assertNotNull(new PageHelper().toString());
  }

  @Test
  public void getLimit() {
    prepareSKEntityPageHelper.setPageHelper(new PageHelper());
    Assert.assertEquals(0, Integer0.null2Zero(prepareSKEntityPageHelper.getPageHelper().getLimit()));
  }

  @Test
  public void selectSql() throws Exception {
    prepareSKEntityPageHelper.setPageHelper(new PageHelper().setLimit(22).setOffset(-1));
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareSKEntityPageHelper.selectSql()));
  }

  @Test
  public void selectSql2() throws Exception {
    prepareSKEntityPageHelper.setPageHelper(new PageHelper().setLimit(1111).setOffset(33));
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareSKEntityPageHelper.selectSql()));
  }

  @Test
  public void selectSql3() throws Exception {
    prepareSKEntityPageHelper.setPageHelper(new PageHelper().setLimit(1333).setOffset(33));
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareSKEntityPageHelper.selectSql()));
  }
}
