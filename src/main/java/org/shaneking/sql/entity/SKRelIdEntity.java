package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * Relation Entity
 */
@Accessors(chain = true)
@ToString
public abstract class SKRelIdEntity<J> extends SKIdEntity<J> {

  @Column(length = 1, columnDefinition = "COMMENT 'The invalid status of record {Y:invalid,N:valid(default)}'")
  @Getter
  @Setter
  private String invalid;

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
}
