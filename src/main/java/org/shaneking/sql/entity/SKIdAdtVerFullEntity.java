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
public abstract class SKIdAdtVerFullEntity<J> extends SKIdAdtVerEntity<J> {

  /**
   * @see org.shaneking.skava.util.Date0#DATE_TIME
   */
  @Column(length = 20, updatable = false, columnDefinition = "COMMENT 'The creation time of record'")
  @Getter
  @Setter
  private String crtDateTime;

  @Column(length = 40, updatable = false, columnDefinition = "COMMENT 'The creator of record'")
  @Getter
  @Setter
  private String crtUserId;

  /**
   * @see org.shaneking.skava.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The invalid time of record'")
  @Getter
  @Setter
  private String ivdDateTime;

  @Column(length = 40, columnDefinition = "COMMENT 'The invalid operator of record'")
  @Getter
  @Setter
  private String ivdUserId;

  @Override
  public SKIdAdtVerFullEntity<J> initWithUserId(@NonNull String userId) {
    this.initVer().initInvalid();
    return this.setCrtDateTime(String0.null2empty2(this.getCrtDateTime(), Date0.on().dateTime())).setCrtUserId(String0.null2empty2(this.getCrtUserId(), userId));
  }

  @Override
  public SKIdAdtVerFullEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
