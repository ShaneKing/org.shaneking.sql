package sktest.sql;

import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.shaneking.skava.ling.lang.Integer0;
import org.shaneking.skava.t3.jackson.OM3;
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
    Assert.assertEquals(100, Integer0.null2Zero(prepareSKEntityPageHelper.getPageHelper().getLimit()));
  }

  @Test
  public void selectSql() throws Exception {
    prepareSKEntityPageHelper.setPageHelper(new PageHelper().setLimit(99).setOffset(33));
    Assert.assertEquals(new String(Files.toByteArray(sqlFile)).trim(), OM3.writeValueAsString(prepareSKEntityPageHelper.selectSql()));
  }
}