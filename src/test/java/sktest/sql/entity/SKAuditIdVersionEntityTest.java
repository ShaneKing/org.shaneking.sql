package sktest.sql.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.shaneking.skava.ling.lang.String0;
import org.shaneking.skava.t3.jackson.OM3;
import org.shaneking.sql.Keyword0;
import org.shaneking.sql.OperationContent;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.PrepareSKAuditIdVersionEntity;

import java.io.File;
import java.util.Map;

@Slf4j
public class SKAuditIdVersionEntityTest extends SKUnit {
  private static final String SKTEST1_USER_ID = "SKTEST1_USER_ID";
  private static final String SKTEST1_DATE_TIME = "2019-06-23 20:00:07";
  private PrepareSKAuditIdVersionEntity prepareEntity;

  @Override
  public void setUp() {
    super.setUp();
    prepareEntity = new PrepareSKAuditIdVersionEntity();
  }

  @Test
  public void newInstance() {
    Assert.assertNotNull(prepareEntity.toString());
  }

  @Test
  public void fullTableName() {
    Assert.assertEquals("t_prepare_s_k_audit_id_version_entity", prepareEntity.fullTableName());
  }

  @Test
  public void whereOCs() throws Exception {
    Map<String, OperationContent> ocMap = Maps.newHashMap();
    ocMap.put("id", new OperationContent().setOp(Keyword0.IN).setCl(Lists.newArrayList(SKEntityTest.SKTEST1_ID)));
    prepareEntity.setId(SKEntityTest.SKTEST1_ID).setVersion(1).setCreateDatetime(SKTEST1_DATE_TIME).setCreateUserId(SKTEST1_USER_ID).setInvalid(String0.Y).setInvalidDatetime(SKTEST1_DATE_TIME).setInvalidUserId(SKTEST1_USER_ID).setLastModifyDatetime(SKTEST1_DATE_TIME).setLastModifyUserId(SKTEST1_USER_ID).setWhereOCs(ocMap);
    Assert.assertEquals(new String(Files.toByteArray(new File("src/test/java/sktest/sql/entity/prepare/PrepareSKAuditIdVersionEntity_whereOCs.sql"))).trim(), OM3.writeValueAsString(prepareEntity.selectSql()));
  }

  @Test
  public void appendWhereByFields2Sql() throws Exception {
    Map<String, OperationContent> ocMap = Maps.newHashMap();
    ocMap.put("id", new OperationContent().setOp(Keyword0.IN).setCl(Lists.newArrayList(SKEntityTest.SKTEST1_ID)));
    prepareEntity.setId(SKEntityTest.SKTEST1_ID).setVersion(1);
    Assert.assertEquals(new String(Files.toByteArray(new File("src/test/java/sktest/sql/entity/prepare/PrepareSKAuditIdVersionEntity_appendWhereByFields2Sql.sql"))).trim(), OM3.writeValueAsString(prepareEntity.appendWhereByFields2Sql(Lists.newArrayList(Keyword0.SELECT, Keyword0.COUNT_1_, Keyword0.FROM, Keyword0.WHERE, "invalid=?"), Lists.newArrayList(String0.Y), prepareEntity.getFieldNameList())));
  }
}
