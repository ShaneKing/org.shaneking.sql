/*
 * @(#)PrepareSKL10nEntityZone.java		Created at 2018/7/4
 *
 * Copyright (c) ShaneKing All rights reserved.
 * ShaneKing PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package sktest.sql.entity.prepare;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.entity.SKL10nEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKL10nEntityZone extends SKL10nEntity {

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
}
