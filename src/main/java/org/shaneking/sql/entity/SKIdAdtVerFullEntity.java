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
  private String crtDateTime;

  @Column(length = 51, updatable = false, columnDefinition = "COMMENT 'The creator of record'")
  @Getter
  @Setter
  private String crtUserId;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The deleted time of record'")
  @Getter
  @Setter
  private String delDateTime;

  @Column(length = 51, columnDefinition = "COMMENT 'The deleted operator of record'")
  @Getter
  @Setter
  private String delUserId;

  @Override
  public SKIdAdtVerFullEntity<J> initWithUserId(@NonNull String userId) {
    this.initVer().initDeleted();
    return this.setCrtDateTime(String0.null2empty2(this.getCrtDateTime(), Date0.on().dateTime())).setCrtUserId(String0.null2empty2(this.getCrtUserId(), userId));
  }

  @Override
  public SKIdAdtVerFullEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
