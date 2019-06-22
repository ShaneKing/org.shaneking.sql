/*
 * @(#)SKL10nEntity.java		Created at 2017/9/17
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

@Accessors(chain = true)
@ToString(callSuper = true)
public class SKL10nEntity<J> extends SKAuditEntity<J> {
  /**
   * @see org.shaneking.skava.ling.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The creation time zone of record'")
  @Getter
  @Setter
  private String createTimezone;

  /**
   * @see org.shaneking.skava.ling.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The invalid time zone of record'")
  @Getter
  @Setter
  private String invalidTimezone;

  /**
   * @see org.shaneking.skava.ling.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The last modification time zone of record'")
  @Getter
  @Setter
  private String lastModifyTimezone;
}
