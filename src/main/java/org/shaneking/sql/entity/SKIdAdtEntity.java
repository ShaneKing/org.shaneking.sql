package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.lang.String0;
import org.shaneking.skava.util.Date0;

import javax.persistence.Column;
import javax.persistence.Transient;

@Accessors(chain = true)
@ToString
public abstract class SKIdAdtEntity<J> extends SKIdEntity<J> {
  @Transient
  public static final String FIELD__INVALID = "invalid";
  @Transient
  public static final String FIELD__MOD_DATE_TIME = "modDateTime";
  @Transient
  public static final String FIELD__MOD_USER_ID = "modUserId";

  @Column(length = 1, columnDefinition = "COMMENT 'The invalid status of record {Y:invalid,N:valid(default)}'")
  @Getter
  @Setter
  private String invalid;

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

  public SKIdAdtEntity<J> initInvalid() {
    return this.setInvalid(String0.null2empty2(this.getInvalid(), String0.N));
  }

  public SKIdAdtEntity<J> initWithUserId(@NonNull String userId) {
    return this.initInvalid().setModDateTime(String0.null2empty2(this.getModDateTime(), Date0.on().dateTime())).setModUserId(String0.null2empty2(this.getModUserId(), userId));
  }

  public SKIdAdtEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
