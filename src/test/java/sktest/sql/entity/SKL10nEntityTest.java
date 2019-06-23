package sktest.sql.entity;

import org.junit.Test;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.PrepareSKL10nEntity;

public class SKL10nEntityTest extends SKUnit {
  @Test
  public void setter() {
    skPrint(new PrepareSKL10nEntity().setCreateTimezone("0800").setInvalidTimezone("0800").setLastModifyTimezone("0800"));
  }

}
