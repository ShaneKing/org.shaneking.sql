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

  @Column(length = 1, columnDefinition = "COMMENT 'The invalid status of record {Y:Invalid,N:Valid(Default)}'")
  @Getter
  @Setter
  private String invalid;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The last modification time of record'")
  @Getter
  @Setter
  private String lastModifyDatetime;

  @Column(length = 40, columnDefinition = "COMMENT 'The last modified person of record'")
  @Getter
  @Setter
  private String lastModifyUserId;

  public SKIdAdtEntity<J> initInvalid() {
    return this.setInvalid(String0.null2empty2(this.getInvalid(), String0.N));
  }

  public SKIdAdtEntity<J> initWithUserId(@NonNull String userId) {
    this.initInvalid();
    return this.setLastModifyDatetime(String0.null2empty2(this.getLastModifyDatetime(), Date0.on().dateTime())).setLastModifyUserId(String0.null2empty2(this.getLastModifyUserId(), userId));
  }

  public SKIdAdtEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
