/*
 * @(#)SKRefAdtVerFullEntity.java		Created at 2017/9/10
 *
 * Copyright (c) ShaneKing All rights reserved.
 * ShaneKing PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.ling.lang.String0;

import javax.persistence.Column;

/**
 * Relation Entity
 */
@Accessors(chain = true)
@ToString(callSuper = true)
public class SKRelAdtEntity<J> extends SKIdEntity<J> {

  @Column(length = 1, columnDefinition = "COMMENT 'The logic removed status of record {Y:logic removed,N:logic exist(default)}'")
  @Getter
  @Setter
  private String removed = String0.N;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The removed time of record'")
  @Getter
  @Setter
  private String rmvDateTime;

  @Column(length = 40, columnDefinition = "COMMENT 'The removed operator of record'")
  @Getter
  @Setter
  private String rmvUserId;
}
