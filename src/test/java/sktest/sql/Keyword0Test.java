package sktest.sql;

import org.junit.Assert;
import org.junit.Test;
import org.shaneking.sql.Keyword0;
import org.shaneking.test.SKUnit;

public class Keyword0Test extends SKUnit {
  @Test
  public void newInstance() {
    Assert.assertNotNull(new Keyword0().toString());
  }

  @Test
  public void wrapBlack() {
    Assert.assertEquals(" a ", Keyword0.wrapBlack("a"));
  }
}
