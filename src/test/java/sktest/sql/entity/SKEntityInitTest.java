package sktest.sql.entity;

import org.junit.Test;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.*;

public class SKEntityInitTest extends SKUnit {

  @Test
  public void testInitSKIdEntity() {
    skPrint(new PrepareSKIdEntity().initId(SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKIdAdtEntity() {
    skPrint(new PrepareSKIdAdtEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKIdAdtVerEntity() {
    skPrint(new PrepareSKIdAdtVerEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKIdAdtVerFullEntity() {
    skPrint(new PrepareSKIdAdtVerFullEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKL10nAdtEntity() {
    skPrint(new PrepareSKZoneAdtEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKL10nAdtVerEntity() {
    skPrint(new PrepareSKZoneAdtVerEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKL10nAdtVerFullEntity() {
    skPrint(new PrepareSKZoneAdtVerFullEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKRefAdtEntity() {
    skPrint(new PrepareSKRefAdtEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKRefAdtVerEntity() {
    skPrint(new PrepareSKRefAdtVerEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }

  @Test
  public void testInitSKRefAdtVerFullEntity() {
    skPrint(new PrepareSKRefAdtVerFullEntity().initWithUserIdAndId(SKIdAdtVerEntityTest.SKTEST1_USER_ID, SKEntityTest.SKTEST1_ID));
  }
}
