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
public class SKAuditEntity<J> extends SKEntity<J> {

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

  @Column(length = 1, columnDefinition = "COMMENT 'The invalid status of record {Y:Invalid,N:Valid(Default)}'")
  @Getter
  @Setter
  private String invalid;

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

  public SKAuditEntity<J> initWithCreateUserId(@NonNull String createUserId) {
    return this.setCreateDatetime(String0.null2empty2(this.getCreateDatetime(), Date0.on().dateTime())).setCreateUserId(String0.null2empty2(this.getCreateUserId(), createUserId)).setInvalid(String0.null2empty2(this.getInvalid(), String0.N));
  }
}
