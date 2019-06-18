/*
 * @(#)SKRefEntity.java		Created at 2017/9/10
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
 * Used for upload file
 */
@Accessors(chain = true)
@ToString(callSuper = true)
public class SKRefEntity<J> extends SKAuditEntity<J> {

  @Column(length = 40, columnDefinition = "COMMENT '关联唯一标识'")
  @Getter
  @Setter
  private String refId;

  @Column(length = 40, columnDefinition = "COMMENT '关联类型'")
  @Getter
  @Setter
  private String refType;
}
