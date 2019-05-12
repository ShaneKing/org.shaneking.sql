/*
 * @(#)AddressEntityTest.java		Created at 2017/9/10
 *
 * Copyright (c) ShaneKing All rights reserved.
 * ShaneKing PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package sktest.sql.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sktest.skava.SKUnit;
import sktest.sql.entity.prepare.PrepareSKRefEntityAddress;

import java.util.UUID;

public class SKRefEntityTest extends SKUnit {
  private PrepareSKRefEntityAddress prepareSKRefEntityAddress = new PrepareSKRefEntityAddress();

  @Before
  public void setUp() {
    super.setUp();
    prepareSKRefEntityAddress = new PrepareSKRefEntityAddress();
  }

  @Test
  public void testRefInsertSql() {
    Assert.assertEquals(prepareSKRefEntityAddress.insertSql().toString(), "(insert into testSchema.t_address (version) values (?),[1])");
  }

  @Test
  public void testRefInsertSql2() {
    String uuid = UUID.randomUUID().toString();
    prepareSKRefEntityAddress.setId(uuid).setAddress("Junjin Load No.500").setRefId("").setRefType("");
    skPrint(prepareSKRefEntityAddress);
    Assert.assertEquals(prepareSKRefEntityAddress.insertSql().toString(), "(insert into testSchema.t_address (id,version,address) values (?,?,?),[" + uuid + ", 1, Junjin Load No.500])");
  }

  @Test
  public void testRefSelectSql() {
    Assert.assertEquals(prepareSKRefEntityAddress.selectSql().toString(), "(select id,version,address,postcode,primary,ref_id,ref_type,create_datetime,create_user_id,invalid,invalid_datetime,invalid_user_id,last_modify_datetime,last_modify_user_id from testSchema.t_address where version=?,[1])");
  }

  @Test
  public void testRefSelectSql2() {
    String uuid = UUID.randomUUID().toString();
    prepareSKRefEntityAddress.setId(uuid).setAddress("Junjin Load No.500").setRefId("").setRefType("");
    skPrint(prepareSKRefEntityAddress);
    Assert.assertEquals(prepareSKRefEntityAddress.selectSql().toString(), "(select id,version,address,postcode,primary,ref_id,ref_type,create_datetime,create_user_id,invalid,invalid_datetime,invalid_user_id,last_modify_datetime,last_modify_user_id from testSchema.t_address where id=? and version=? and address=?,[" + uuid + ", 1, Junjin Load No.500])");
  }

  @Test
  public void testRefUpdateByIdAndVersionSql() {
    String uuid = UUID.randomUUID().toString();
    prepareSKRefEntityAddress.setId(uuid).setAddress("Junjin Load No.500").setRefId("").setRefType("");
    skPrint(prepareSKRefEntityAddress);
    Assert.assertEquals(prepareSKRefEntityAddress.updateByIdAndVersionSql().toString(), "(update testSchema.t_address set version=?,address=? where id=? and version=?,[2, Junjin Load No.500, " + uuid + ", 1])");
  }
}



