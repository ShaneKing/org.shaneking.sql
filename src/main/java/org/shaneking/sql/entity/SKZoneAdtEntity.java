package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.util.Date0;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * Just reference
 */
@Accessors(chain = true)
@ToString
public abstract class SKZoneAdtEntity<J> extends SKIdAdtEntity<J> {
  @Transient
  public static final String FIELD__MOD_ZONE = "modZone";
  /**
   * @see org.shaneking.skava.util.Date0#XXX
   */
  @Column(length = 10, columnDefinition = "COMMENT 'The last modification time zone of record'")
  @Getter
  @Setter
  private String modZone;

  public SKZoneAdtEntity<J> initWithUserId(@NonNull String userId) {
    super.initWithUserId(userId);
    return this.setModZone(Date0.on().zone());
  }
}
