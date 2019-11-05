package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.ling.lang.String0;
import org.shaneking.skava.ling.util.Date0;

import javax.persistence.Column;

@Accessors(chain = true)
@ToString(callSuper = true)
public class SKIdAdtEntity<J> extends SKIdEntity<J> {

  @Column(length = 1, columnDefinition = "COMMENT 'The logic deleted status of record {Y:logic deleted,N:logic exist(default)}'")
  @Getter
  @Setter
  private String deleted = String0.N;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The last modification date time of record'")
  @Getter
  @Setter
  private String modDateTime;

  @Column(length = 40, columnDefinition = "COMMENT 'The last modified person of record'")
  @Getter
  @Setter
  private String modUserId;

  public SKIdAdtEntity<J> initDeleted() {
    return this.setDeleted(String0.null2empty2(this.getDeleted(), String0.N));
  }

  public SKIdAdtEntity<J> initWithUserId(@NonNull String userId) {
    return this.initDeleted().setModDateTime(String0.null2empty2(this.getModDateTime(), Date0.on().dateTime())).setModUserId(String0.null2empty2(this.getModUserId(), userId));
  }

  public SKIdAdtEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
