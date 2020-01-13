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
@ToString
public abstract class SKZoneAdtEntity<J> extends SKIdAdtEntity<J> {

  /**
   * @see org.shaneking.skava.util.Date0#XXX
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The last modification time zone of record'")
  @Getter
  @Setter
  private String modZone;
}
