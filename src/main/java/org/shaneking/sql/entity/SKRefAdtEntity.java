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

import javax.persistence.Column;

/**
 * Just reference, used for upload file
 */
@Accessors(chain = true)
@ToString(callSuper = true)
public class SKRefAdtEntity<J> extends SKIdAdtEntity<J> {

  @Column(length = 51, columnDefinition = "COMMENT 'Relation unique flag'")
  @Getter
  @Setter
  private String refId;

  @Column(length = 51, columnDefinition = "COMMENT 'Relation type'")
  @Getter
  @Setter
  private String refType;
}
