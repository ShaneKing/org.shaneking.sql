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
public class SKIdAdtVerFullEntity<J> extends SKIdAdtVerEntity<J> {

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, updatable = false, columnDefinition = "COMMENT 'The creation time of record'")
  @Getter
  @Setter
  private String createDatetime;

  @Column(length = 40, updatable = false, columnDefinition = "COMMENT 'The creator of record'")
  @Getter
  @Setter
  private String createUserId;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The invalid time of record'")
  @Getter
  @Setter
  private String invalidDatetime;

  @Column(length = 40, columnDefinition = "COMMENT 'The invalid operator of record'")
  @Getter
  @Setter
  private String invalidUserId;

  public SKIdAdtVerFullEntity<J> initWithUserId(@NonNull String userId) {
    this.initVersion().initInvalid();
    return this.setCreateDatetime(String0.null2empty2(this.getLastModifyDatetime(), Date0.on().dateTime())).setCreateUserId(String0.null2empty2(this.getLastModifyUserId(), userId));
  }

  public SKIdAdtVerFullEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
