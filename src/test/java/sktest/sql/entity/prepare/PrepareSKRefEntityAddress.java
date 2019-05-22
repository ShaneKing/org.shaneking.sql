/*
 * @(#)PrepareSKRefEntityAddress.java		Created at 2018/2/4
 *
 * Copyright (c) ShaneKing All rights reserved.
 * ShaneKing PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package sktest.sql.entity.prepare;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.entity.SKRefEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Accessors(chain = true)
@Table(name = "t_address", schema = "testSchema")
@ToString(callSuper = true)
public class PrepareSKRefEntityAddress extends SKRefEntity {

  @Column(length = 40, updatable = false)
  @Getter
  @Id
  @Setter
  private String id;

  @Column(length = 20)
  @Getter
  @Setter
  @Version
  private Integer version = 1;
  /**
   * InnoDB prefix index max 767 bytes(utf8:767/3=255char;gbk:767/2=383char)
   */
  @Column(length = 255)
  @Getter
  @Setter
  private String address;

  @Column(length = 6)
  @Getter
  @Setter
  private String postcode;

  @Column(length = 1)
  @Getter
  @Setter
  private String primary;//0|1\
}
