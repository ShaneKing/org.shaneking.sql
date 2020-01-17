package sktest.sql.entity;

import org.junit.Assert;
import org.junit.Test;
import org.shaneking.sql.entity.SKIdAdtEntity;
import org.shaneking.sql.entity.SKIdEntity;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.*;

public class PrepareInitTest extends SKUnit {
  @Test
  public void testSKIdAdtEntity() {
    Assert.assertTrue(new PrepareSKIdAdtEntity().initWithUserIdAndId(SKIdAdtEntity.FIELD__MOD_USER_ID, SKIdEntity.FIELD__ID).toString().contains("PrepareSKIdAdtEntity(super=SKIdAdtEntity(invalid=N, modDateTime="));
  }

  @Test
  public void testSKIdAdtVerEntity() {
    Assert.assertEquals("PrepareSKIdAdtVerEntity(super=SKIdAdtVerEntity(ver=0))", new PrepareSKIdAdtVerEntity().initWithUserIdAndId(SKIdAdtEntity.FIELD__MOD_USER_ID, SKIdEntity.FIELD__ID).toString());
  }

  @Test
  public void testSKIdAdtVerFullEntity() {
    Assert.assertTrue(new PrepareSKIdAdtVerFullEntity().initWithUserIdAndId(SKIdAdtEntity.FIELD__MOD_USER_ID, SKIdEntity.FIELD__ID).toString().startsWith("PrepareSKIdAdtVerFullEntity(super=SKIdAdtVerFullEntity(crtDateTime="));
  }

  @Test
  public void testSKIdEntity() {
    Assert.assertEquals("PrepareSKIdEntity(super=SKIdEntity(id=id))", new PrepareSKIdEntity().initId(SKIdEntity.FIELD__ID).toString());
  }

  @Test
  public void testSKRelIdEntity() {
    Assert.assertEquals("PrepareSKRelIdEntity(super=SKRelIdEntity(invalid=N, ivdDateTime=null, ivdUserId=null))", new PrepareSKRelIdEntity().initInvalid().toString());
  }

  @Test
  public void testSKZoneAdtEntity() {
    Assert.assertTrue(new PrepareSKZoneAdtEntity().initWithUserIdAndId(SKIdAdtEntity.FIELD__MOD_USER_ID, SKIdEntity.FIELD__ID).toString().startsWith("PrepareSKZoneAdtEntity(super=SKZoneAdtEntity(modZone="));
  }

  @Test
  public void testSKZoneAdtVerEntity() {
    Assert.assertTrue(new PrepareSKZoneAdtVerEntity().initWithUserIdAndId(SKIdAdtEntity.FIELD__MOD_USER_ID, SKIdEntity.FIELD__ID).toString().startsWith("PrepareSKZoneAdtVerEntity(super=SKZoneAdtVerEntity(modZone="));
  }

  @Test
  public void testSKZoneAdtVerFullEntity() {
    Assert.assertTrue(new PrepareSKZoneAdtVerFullEntity().initWithUserIdAndId(SKIdAdtEntity.FIELD__MOD_USER_ID, SKIdEntity.FIELD__ID).toString().startsWith("PrepareSKZoneAdtVerFullEntity(super=SKZoneAdtVerFullEntity(crtZone="));
  }
}
