package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.util.Date0;

import javax.persistence.Column;

/**
 * Just reference
 */
@Accessors(chain = true)
@ToString
public abstract class SKZoneAdtVerFullEntity<J> extends SKIdAdtVerFullEntity<J> {
  /**
   * @see org.shaneking.skava.util.Date0#XXX
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The creation time zone of record'")
  @Getter
  @Setter
  private String crtZone;

  /**
   * @see org.shaneking.skava.util.Date0#XXX
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The invalid time zone of record'")
  @Getter
  @Setter
  private String ivdZone;

  /**
   * @see org.shaneking.skava.util.Date0#XXX
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The last modification time zone of record'")
  @Getter
  @Setter
  private String modZone;

  public SKZoneAdtVerFullEntity<J> initWithUserId(@NonNull String userId) {
    super.initWithUserId(userId);
    return this.setCrtZone(Date0.on().zone());
  }
}
