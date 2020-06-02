package sktest.sql.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.shaneking.test.SKUnit;
import sktest.sql.entity.prepare.PrepareSKIdAdtVerEntity;

@Slf4j
public class EntityFullTableNameTest extends SKUnit {
    private PrepareSKIdAdtVerEntity prepareSKIdAdtVerEntity;

    @Override
    public void tstSetUp() {
      prepareSKIdAdtVerEntity = new PrepareSKIdAdtVerEntity();
    }

    @Test
    public void fullTableName() {
    Assert.assertEquals("t_prepare_s_k_id_adt_ver_entity", prepareSKIdAdtVerEntity.fullTableName());
  }
}
