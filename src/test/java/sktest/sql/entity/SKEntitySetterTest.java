package sktest.sql.entity;

import org.junit.Test;
import org.shaneking.skava.ling.util.Date0;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.*;

public class SKEntitySetterTest extends SKUnit {

  @Test
  public void testSetterSKIdAdtVerFullEntity() {
    skPrint(new PrepareSKIdAdtVerFullEntity().setCreateDatetime(Date0.on().dateTime()).setCreateUserId(SKIdAdtVerEntityTest.SKTEST1_USER_ID).setInvalidDatetime(Date0.on().dateTime()).setInvalidUserId(SKIdAdtVerEntityTest.SKTEST1_USER_ID));
  }

  @Test
  public void testSetterSKL10nAdtEntity() {
    skPrint(new PrepareSKL10nAdtEntity().setLastModifyTimezone("+0800"));
  }

  @Test
  public void testSetterSKL10nAdtVerEntity() {
    skPrint(new PrepareSKL10nAdtVerEntity().setLastModifyTimezone("+0800"));
  }

  @Test
  public void testSetterSKL10nAdtVerFullEntity() {
    skPrint(new PrepareSKL10nAdtVerFullEntity().setCreateTimezone("+0800").setInvalidTimezone("+0800").setLastModifyTimezone("+0800"));
  }

  @Test
  public void testSetterSKRefAdtEntity() {
    skPrint(new PrepareSKRefAdtEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id"));
  }

  @Test
  public void testSetterSKRefAdtVerEntity() {
    skPrint(new PrepareSKRefAdtVerEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id"));
  }

  @Test
  public void testSetterSKRefAdtVerFullEntity() {
    skPrint(new PrepareSKRefAdtVerFullEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id"));
  }

}
