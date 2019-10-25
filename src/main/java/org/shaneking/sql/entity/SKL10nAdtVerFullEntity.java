/*
 * @(#)SKL10nAdtEntity.java		Created at 2017/9/17
 *
 * Copyright (c) ShaneKing All rights reserved.
 * ShaneKing PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * Just reference
 */
@Accessors(chain = true)
@ToString(callSuper = true)
public class SKL10nAdtVerFullEntity<J> extends SKIdAdtVerFullEntity<J> {
  /**
   * @see org.shaneking.skava.ling.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The creation time zone of record'")
  @Getter
  @Setter
  private String addZone;

  /**
   * @see org.shaneking.skava.ling.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The deleted time zone of record'")
  @Getter
  @Setter
  private String delZone;

  /**
   * @see org.shaneking.skava.ling.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The last modification time zone of record'")
  @Getter
  @Setter
  private String modZone;
}
