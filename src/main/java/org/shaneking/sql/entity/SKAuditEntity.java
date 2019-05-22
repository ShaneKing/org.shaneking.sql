package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Accessors(chain = true)
@ToString(callSuper = true)
public class SKAuditEntity<J> extends SKEntity<J> {

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, updatable = false)
  @Getter
  @Setter
  private String createDatetime;

  @Column(length = 40, updatable = false)
  @Getter
  @Setter
  private String createUserId;

  @Column(length = 1)
  @Getter
  @Setter
  private String invalid;//Y|N(default)

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20)
  @Getter
  @Setter
  private String invalidDatetime;

  @Column(length = 40)
  @Getter
  @Setter
  private String invalidUserId;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20)
  @Getter
  @Setter
  private String lastModifyDatetime;

  @Column(length = 40)
  @Getter
  @Setter
  private String lastModifyUserId;
}
