package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Accessors(chain = true)
@ToString(callSuper = true)
public class SKAuditEntity<J> extends SKEntity<J> {
  public static final String INVALID__Y = "Y";
  public static final String INVALID__N = "N";

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, updatable = false, columnDefinition = "COMMENT '创建时间'")
  @Getter
  @Setter
  private String createDatetime;

  @Column(length = 40, updatable = false, columnDefinition = "COMMENT '创建人'")
  @Getter
  @Setter
  private String createUserId;

  @Column(length = 1, columnDefinition = "COMMENT '是否失效{Y:已失效,N:未失效(默认值)}'")
  @Getter
  @Setter
  private String invalid;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT '失效时间'")
  @Getter
  @Setter
  private String invalidDatetime;

  @Column(length = 40, columnDefinition = "COMMENT '失效人'")
  @Getter
  @Setter
  private String invalidUserId;

  /**
   * @see org.shaneking.skava.ling.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT '最后修改时间'")
  @Getter
  @Setter
  private String lastModifyDatetime;

  @Column(length = 40, columnDefinition = "COMMENT '最后修改人'")
  @Getter
  @Setter
  private String lastModifyUserId;
}
