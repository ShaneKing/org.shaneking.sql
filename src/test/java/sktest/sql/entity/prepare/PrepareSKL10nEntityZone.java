/*
 * @(#)PrepareSKL10nEntityZone.java		Created at 2018/7/4
 *
 * Copyright (c) ShaneKing All rights reserved.
 * ShaneKing PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package sktest.sql.entity.prepare;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.entity.SKL10nEntity;

import javax.persistence.Table;

@Accessors(chain = true)
@ToString(callSuper = true, includeFieldNames = true)
@Table
public class PrepareSKL10nEntityZone extends SKL10nEntity {
}
