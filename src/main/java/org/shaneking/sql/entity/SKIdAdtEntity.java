package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.lang.String0;
import org.shaneking.skava.util.Date0;

import javax.persistence.Column;

@Accessors(chain = true)
@ToString
public class SKIdAdtEntity<J> extends SKIdEntity<J> {

  @Column(length = 1, columnDefinition = "COMMENT 'The freeze status of record {Y:freezed,N:actived}'")
  @Getter
  @Setter
  private String freezed;

  /**
   * @see org.shaneking.skava.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The last modification date time of record'")
  @Getter
  @Setter
  private String modDateTime;

  @Column(length = 40, columnDefinition = "COMMENT 'The last modified person of record'")
  @Getter
  @Setter
  private String modUserId;

  public SKIdAdtEntity<J> initFreezed() {
    return this.setFreezed(String0.null2empty2(this.getFreezed(), String0.N));
  }

  public SKIdAdtEntity<J> initWithUserId(@NonNull String userId) {
    return this.initFreezed().setModDateTime(String0.null2empty2(this.getModDateTime(), Date0.on().dateTime())).setModUserId(String0.null2empty2(this.getModUserId(), userId));
  }

  public SKIdAdtEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
