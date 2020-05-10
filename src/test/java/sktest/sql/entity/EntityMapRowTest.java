package sktest.sql.entity;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.shaneking.jackson.databind.OM3;
import org.shaneking.sql.entity.SKIdAdtVerEntity;
import org.shaneking.sql.entity.SKIdEntity;
import org.shaneking.test.SKUnit;
import sktest.sql.entity.prepare.PrepareSqlSKIdAdtVerEntity;

import java.sql.ResultSet;

@RunWith(MockitoJUnitRunner.class)
public class EntityMapRowTest extends SKUnit {
    @Mock
    private ResultSet resultSet;

    @Test
    public void mapRow() throws Exception {
        PrepareSqlSKIdAdtVerEntity prepareSqlSKIdAdtVerEntity = new PrepareSqlSKIdAdtVerEntity();

        Mockito.when(resultSet.getString(SKIdEntity.FIELD__ID)).thenReturn(EntitySqlTest.SKTEST1_ID);
        Mockito.when(resultSet.getInt(SKIdAdtVerEntity.FIELD__VER)).thenReturn(1);
        prepareSqlSKIdAdtVerEntity.setSelectList(Lists.newArrayList(SKIdEntity.FIELD__ID, SKIdAdtVerEntity.FIELD__VER));
    prepareSqlSKIdAdtVerEntity.mapRow(resultSet);

    Assert.assertEquals("{\"selectList\":[\"id\",\"ver\"],\"id\":\"sktest1_id\",\"ver\":1}", OM3.writeValueAsString(prepareSqlSKIdAdtVerEntity));
  }
}
