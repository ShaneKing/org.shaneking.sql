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
public class SKZoneAdtVerFullEntity<J> extends SKIdAdtVerFullEntity<J> {
  /**
   * @see org.shaneking.skava.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The creation time zone of record'")
  @Getter
  @Setter
  private String crtZone;

  /**
   * @see org.shaneking.skava.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The invalid time zone of record'")
  @Getter
  @Setter
  private String ivdZone;

  /**
   * @see org.shaneking.skava.util.Date0#H_MI_S
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The last modification time zone of record'")
  @Getter
  @Setter
  private String modZone;
}
