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
@ToString
public abstract class SKRefAdtEntity<J> extends SKIdAdtEntity<J> {

  @Column(length = 40, columnDefinition = "COMMENT 'Relation unique flag'")
  @Getter
  @Setter
  private String refId;

  @Column(length = 40, columnDefinition = "COMMENT 'Relation type'")
  @Getter
  @Setter
  private String refType;
}
