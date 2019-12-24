package sktest.sql.entity;

import org.junit.Assert;
import org.junit.Test;
import org.shaneking.jackson.databind.OM3;
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
    Assert.assertEquals("{\"deleted\":\"N\",\"modZone\":\"+0800\"}", OM3.writeValueAsString(new PrepareSKL10nAdtEntity().setModZone("+0800")));
  }

  @Test
  public void testSetterSKL10nAdtVerEntity() {
    Assert.assertEquals("{\"deleted\":\"N\",\"modZone\":\"+0800\"}", OM3.writeValueAsString(new PrepareSKL10nAdtVerEntity().setModZone("+0800")));
  }

  @Test
  public void testSetterSKL10nAdtVerFullEntity() {
    Assert.assertEquals("{\"deleted\":\"N\",\"crtZone\":\"+0800\",\"delZone\":\"+0800\",\"modZone\":\"+0800\"}", OM3.writeValueAsString(new PrepareSKL10nAdtVerFullEntity().setCrtZone("+0800").setDelZone("+0800").setModZone("+0800")));
  }

  @Test
  public void testSetterSKRefAdtEntity() {
    Assert.assertEquals("{\"deleted\":\"N\",\"refId\":\"sktest1_ref_id\",\"refType\":\"sktest1_ref_type\"}", OM3.writeValueAsString(new PrepareSKRefAdtEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id")));
  }

  @Test
  public void testSetterSKRefAdtVerEntity() {
    Assert.assertEquals("{\"deleted\":\"N\",\"refId\":\"sktest1_ref_id\",\"refType\":\"sktest1_ref_type\"}", OM3.writeValueAsString(new PrepareSKRefAdtVerEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id")));
  }

  @Test
  public void testSetterSKRefAdtVerFullEntity() {
    Assert.assertEquals("{\"deleted\":\"N\",\"refId\":\"sktest1_ref_id\",\"refType\":\"sktest1_ref_type\"}", OM3.writeValueAsString(new PrepareSKRefAdtVerFullEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id")));
  }

}
