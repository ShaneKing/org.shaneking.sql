/*
 * @(#)SKRefAdtVerFullEntity.java		Created at 2017/9/10
 *
 * Copyright (c) ShaneKing All rights reserved.
 * ShaneKing PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.shaneking.sql.entity;

import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Relation Entity
 */
@Accessors(chain = true)
@ToString(callSuper = true)
public class SKRelIdEntity<J> extends SKIdEntity<J> {
}
