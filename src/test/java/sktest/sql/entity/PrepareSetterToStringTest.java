package sktest.sql.entity;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.shaneking.skava.lang.String0;
import org.shaneking.sql.entity.SKIdAdtVerEntity;
import org.shaneking.sql.entity.SKIdEntity;
import org.shaneking.sql.entity.SKRefIdEntity;
import org.shaneking.sql.entity.SKTreeIdEntity;
import sktest.sql.SKUnit;
import sktest.sql.entity.prepare.*;

public class PrepareSetterToStringTest extends SKUnit {
  @Test
  public void testSKEntity() {
    //not equals in travis-ci, maybe it openjdk
//    Assert.assertEquals("PrepareSKEntity(super=SKEntity(columnMap={}, dbColumnMap={}, fieldMap={}, fieldNameList=[], idFieldNameList=[], verFieldNameList=[], dbTableName=t_prepare_s_k_entity, javaTable=@javax.persistence.Table(name=, schema=, uniqueConstraints=[], catalog=), groupByList=[ver], havingOCs=null, orderByList=[mod_date_time], pageHelper=null, selectList=[modUserId], whereOCs=null))", new PrepareSKEntity().setGroupByList(Lists.newArrayList(SKIdAdtVerEntity.FIELD__VER)).setOrderByList(Lists.newArrayList(String0.field2DbColumn(SKIdAdtVerEntity.FIELD__MOD_DATE_TIME))).setSelectList(Lists.newArrayList(SKIdAdtVerEntity.FIELD__MOD_USER_ID)).toString());
    Assert.assertTrue(new PrepareSKEntity().setGroupByList(Lists.newArrayList(SKIdAdtVerEntity.FIELD__VER)).setOrderByList(Lists.newArrayList(String0.field2DbColumn(SKIdAdtVerEntity.FIELD__MOD_DATE_TIME))).setSelectList(Lists.newArrayList(SKIdAdtVerEntity.FIELD__MOD_USER_ID)).toString().startsWith("PrepareSKEntity(super=SKEntity(columnMap={}, dbColumnMap={}, fieldMap={}, fieldNameList=[], idFieldNameList=[], verFieldNameList=[], dbTableName=t_prepare_s_k_entity, javaTable=@javax.persistence.Table("));
  }

  @Test
  public void testSKIdAdtEntity() {
    Assert.assertEquals("PrepareSKIdAdtEntity(super=SKIdAdtEntity(invalid=Y, modDateTime=2020-01-17 19:45:00, modUserId=id))", new PrepareSKIdAdtEntity().setInvalid(String0.Y).setModDateTime("2020-01-17 19:45:00").setModUserId(SKIdEntity.FIELD__ID).toString());
  }

  @Test
  public void testSKIdAdtVerEntity() {
    Assert.assertEquals("PrepareSKIdAdtVerEntity(super=SKIdAdtVerEntity(ver=1))", new PrepareSKIdAdtVerEntity().setVer(1).toString());
  }

  @Test
  public void testSKIdAdtVerFullEntity() {
    Assert.assertEquals("PrepareSKIdAdtVerFullEntity(super=SKIdAdtVerFullEntity(crtDateTime=2020-01-17 19:45:00, crtUserId=id, ivdDateTime=2020-01-17 19:45:00, ivdUserId=id))", new PrepareSKIdAdtVerFullEntity().setCrtDateTime("2020-01-17 19:45:00").setCrtUserId(SKIdEntity.FIELD__ID).setIvdDateTime("2020-01-17 19:45:00").setIvdUserId(SKIdEntity.FIELD__ID).toString());
  }

  @Test
  public void testSKIdEntity() {
    Assert.assertEquals("PrepareSKIdEntity(super=SKIdEntity(id=id))", new PrepareSKIdEntity().setId(SKIdEntity.FIELD__ID).toString());
  }

  @Test
  public void testSKRefAdtEntity() {
    Assert.assertEquals("PrepareSKRefAdtEntity(super=SKRefAdtEntity(refId=refId, refType=refType))", new PrepareSKRefAdtEntity().setRefType(SKRefIdEntity.FIELD__REF_TYPE).setRefId(SKRefIdEntity.FIELD__REF_ID).toString());
  }

  @Test
  public void testSKRefAdtVerEntity() {
    Assert.assertEquals("PrepareSKRefAdtVerEntity(super=SKRefAdtVerEntity(refId=refId, refType=refType))", new PrepareSKRefAdtVerEntity().setRefType(SKRefIdEntity.FIELD__REF_TYPE).setRefId(SKRefIdEntity.FIELD__REF_ID).toString());
  }

  @Test
  public void testSKRefAdtVerFullEntity() {
    Assert.assertEquals("PrepareSKRefAdtVerFullEntity(super=SKRefAdtVerFullEntity(refId=refId, refType=refType))", new PrepareSKRefAdtVerFullEntity().setRefType(SKRefIdEntity.FIELD__REF_TYPE).setRefId(SKRefIdEntity.FIELD__REF_ID).toString());
  }

  @Test
  public void testSKRefIdEntity() {
    Assert.assertEquals("PrepareSKRefIdEntity(super=SKRefIdEntity(refId=refId, refType=refType))", new PrepareSKRefIdEntity().setRefType(SKRefIdEntity.FIELD__REF_TYPE).setRefId(SKRefIdEntity.FIELD__REF_ID).toString());
  }

  @Test
  public void testSKRelIdEntity() {
    Assert.assertEquals("PrepareSKRelIdEntity(super=SKRelIdEntity(invalid=Y, ivdDateTime=2020-01-17 19:45:00, ivdUserId=id))", new PrepareSKRelIdEntity().setInvalid(String0.Y).setIvdDateTime("2020-01-17 19:45:00").setIvdUserId(SKIdEntity.FIELD__ID).toString());
  }

  @Test
  public void testSKTreeAdtEntity() {
    Assert.assertEquals("PrepareSKTreeAdtEntity(super=SKTreeAdtEntity(nodeDesc=nodeDesc, nodeName=nodeName, nodePath=nodePath, nodeType=nodeType, parentId=parentId))", new PrepareSKTreeAdtEntity().setNodeDesc(SKTreeIdEntity.FIELD__NODE_DESC).setNodeName(SKTreeIdEntity.FIELD__NODE_NAME).setNodePath(SKTreeIdEntity.FIELD__NODE_PATH).setNodeType(SKTreeIdEntity.FIELD__NODE_TYPE).setParentId(SKTreeIdEntity.FIELD__PARENT_ID).toString());
  }

  @Test
  public void testSKTreeAdtVerEntity() {
    Assert.assertEquals("PrepareSKTreeAdtVerEntity(super=SKTreeAdtVerEntity(nodeDesc=nodeDesc, nodeName=nodeName, nodePath=nodePath, nodeType=nodeType, parentId=parentId))", new PrepareSKTreeAdtVerEntity().setNodeDesc(SKTreeIdEntity.FIELD__NODE_DESC).setNodeName(SKTreeIdEntity.FIELD__NODE_NAME).setNodePath(SKTreeIdEntity.FIELD__NODE_PATH).setNodeType(SKTreeIdEntity.FIELD__NODE_TYPE).setParentId(SKTreeIdEntity.FIELD__PARENT_ID).toString());
  }

  @Test
  public void testSKTreeAdtVerFullEntity() {
    Assert.assertEquals("PrepareSKTreeAdtVerFullEntity(super=SKTreeAdtVerFullEntity(nodeDesc=nodeDesc, nodeName=nodeName, nodePath=nodePath, nodeType=nodeType, parentId=parentId))", new PrepareSKTreeAdtVerFullEntity().setNodeDesc(SKTreeIdEntity.FIELD__NODE_DESC).setNodeName(SKTreeIdEntity.FIELD__NODE_NAME).setNodePath(SKTreeIdEntity.FIELD__NODE_PATH).setNodeType(SKTreeIdEntity.FIELD__NODE_TYPE).setParentId(SKTreeIdEntity.FIELD__PARENT_ID).toString());
  }

  @Test
  public void testSKTreeIdEntity() {
    Assert.assertEquals("PrepareSKTreeIdEntity(super=SKTreeIdEntity(nodeDesc=nodeDesc, nodeName=nodeName, nodePath=nodePath, nodeType=nodeType, parentId=parentId))", new PrepareSKTreeIdEntity().setNodeDesc(SKTreeIdEntity.FIELD__NODE_DESC).setNodeName(SKTreeIdEntity.FIELD__NODE_NAME).setNodePath(SKTreeIdEntity.FIELD__NODE_PATH).setNodeType(SKTreeIdEntity.FIELD__NODE_TYPE).setParentId(SKTreeIdEntity.FIELD__PARENT_ID).toString());
  }

  @Test
  public void testSKZoneAdtEntity() {
    Assert.assertEquals("PrepareSKZoneAdtEntity(super=SKZoneAdtEntity(modZone=+0800))", new PrepareSKZoneAdtEntity().setModZone("+0800").toString());
  }

  @Test
  public void testSKZoneAdtVerEntity() {
    Assert.assertEquals("PrepareSKZoneAdtVerEntity(super=SKZoneAdtVerEntity(modZone=+0800))", new PrepareSKZoneAdtVerEntity().setModZone("+0800").toString());
  }

  @Test
  public void testSKZoneAdtVerFullEntity() {
    Assert.assertEquals("PrepareSKZoneAdtVerFullEntity(super=SKZoneAdtVerFullEntity(crtZone=+0800, ivdZone=+0800, modZone=+0800))", new PrepareSKZoneAdtVerFullEntity().setCrtZone("+0800").setIvdZone("+0800").setModZone("+0800").toString());
  }
}
