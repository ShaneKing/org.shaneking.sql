package sktest.sql.entity;

import org.junit.Test;
import org.shaneking.skava.util.Date0;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.*;

public class SKEntitySetterTest extends SKUnit {

  @Test
  public void testSetterSKIdAdtVerFullEntity() {
    skPrint(new PrepareSKIdAdtVerFullEntity().setCrtDateTime(Date0.on().dateTime()).setCrtUserId(SKIdAdtVerEntityTest.SKTEST1_USER_ID).setDelDateTime(Date0.on().dateTime()).setDelUserId(SKIdAdtVerEntityTest.SKTEST1_USER_ID));
  }

  @Test
  public void testSetterSKL10nAdtEntity() {
    skPrint(new PrepareSKL10nAdtEntity().setModZone("+0800"));
  }

  @Test
  public void testSetterSKL10nAdtVerEntity() {
    skPrint(new PrepareSKL10nAdtVerEntity().setModZone("+0800"));
  }

  @Test
  public void testSetterSKL10nAdtVerFullEntity() {
    skPrint(new PrepareSKL10nAdtVerFullEntity().setCrtZone("+0800").setDelZone("+0800").setModZone("+0800"));
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
