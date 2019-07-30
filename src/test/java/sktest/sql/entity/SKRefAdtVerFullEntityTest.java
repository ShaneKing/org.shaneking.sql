package sktest.sql.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.shaneking.skava.t3.jackson.OM3;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.PrepareSKRefAdtVerFullEntity;

import java.sql.ResultSet;

@RunWith(MockitoJUnitRunner.class)
public class SKRefAdtVerFullEntityTest extends SKUnit {
  @Mock
  private ResultSet resultSet;

  @Test
  public void setter() {
    skPrint(new PrepareSKRefAdtVerFullEntity().setRefId(SKEntityTest.SKTEST1_ID).setRefType("sktest1_ref_type"));
  }

  @Test
  public void mapRow() throws Exception {
    PrepareSKRefAdtVerFullEntity prepareEntity = new PrepareSKRefAdtVerFullEntity();

    Mockito.when(resultSet.getString("id")).thenReturn(SKEntityTest.SKTEST1_ID);
    Mockito.when(resultSet.getInt("version")).thenReturn(1);

    Mockito.when(resultSet.getInt("int_for_map_row_exception")).thenReturn(2);

    prepareEntity.mapRow(resultSet);

    Assert.assertEquals("{\"id\":\"sktest1_id\",\"version\":1}", OM3.writeValueAsString(prepareEntity));
  }
}
