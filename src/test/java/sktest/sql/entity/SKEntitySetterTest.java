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
    skPrint(new PrepareSKIdAdtVerFullEntity().setCrtDateTime(Date0.on().dateTime()).setCrtUserId(SKIdAdtVerEntityTest.SKTEST1_USER_ID).setFrzDateTime(Date0.on().dateTime()).setFrzUserId(SKIdAdtVerEntityTest.SKTEST1_USER_ID));
  }

  @Test
  public void testSetterSKZoneAdtEntity() {
    Assert.assertEquals("{\"modZone\":\"+0800\"}", OM3.writeValueAsString(new PrepareSKZoneAdtEntity().setModZone("+0800")));
  }

  @Test
  public void testSetterSKZoneAdtVerEntity() {
    Assert.assertEquals("{\"modZone\":\"+0800\"}", OM3.writeValueAsString(new PrepareSKZoneAdtVerEntity().setModZone("+0800")));
  }

  @Test
  public void testSetterSKZoneAdtVerFullEntity() {
    Assert.assertEquals("{\"crtZone\":\"+0800\",\"frzZone\":\"+0800\",\"modZone\":\"+0800\"}", OM3.writeValueAsString(new PrepareSKZoneAdtVerFullEntity().setCrtZone("+0800").setFrzZone("+0800").setModZone("+0800")));
  }

  @Test
  public void testSetterSKRefAdtEntity() {
    Assert.assertEquals("{\"refId\":\"sktest1_ref_id\",\"refType\":\"sktest1_ref_type\"}", OM3.writeValueAsString(new PrepareSKRefAdtEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id")));
  }

  @Test
  public void testSetterSKRefAdtVerEntity() {
    Assert.assertEquals("{\"refId\":\"sktest1_ref_id\",\"refType\":\"sktest1_ref_type\"}", OM3.writeValueAsString(new PrepareSKRefAdtVerEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id")));
  }

  @Test
  public void testSetterSKRefAdtVerFullEntity() {
    Assert.assertEquals("{\"refId\":\"sktest1_ref_id\",\"refType\":\"sktest1_ref_type\"}", OM3.writeValueAsString(new PrepareSKRefAdtVerFullEntity().setRefType("sktest1_ref_type").setRefId("sktest1_ref_id")));
  }

}
