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
  @Getter
  @Setter
  @Column(length = 20, updatable = false)
  private String createDatetime;

  @Getter
  @Setter
  @Column(length = 40, updatable = false)
  private String createUserId;

  @Getter
  @Setter
  @Column(length = 10)
  private String invalid;//Y|N(default)

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Getter
  @Setter
  @Column(length = 20)
  private String invalidDatetime;

  @Getter
  @Setter
  @Column(length = 40)
  private String invalidUserId;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Getter
  @Setter
  @Column(length = 20)
  private String lastModifyDatetime;

  @Getter
  @Setter
  @Column(length = 40)
  private String lastModifyUserId;
}
