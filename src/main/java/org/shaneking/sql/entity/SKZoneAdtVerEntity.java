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
public abstract class SKZoneAdtVerEntity<J> extends SKIdAdtVerEntity<J> {
  /**
   * @see org.shaneking.skava.util.Date0#XXX
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The last modification time zone of record'")
  @Getter
  @Setter
  private String modZone;

  public SKZoneAdtVerEntity<J> initWithUserId(@NonNull String userId) {
    super.initWithUserId(userId);
    return this.setModZone(Date0.on().zone());
  }
}
