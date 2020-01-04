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
public class SKRelEntity<J> extends SKIdEntity<J> {

  @Column(length = 1, columnDefinition = "COMMENT 'The freeze status of record {Y:freezed,N:actived}'")
  @Getter
  @Setter
  private String freezed;

  /**
   * @see org.shaneking.skava.util.Date0#DATE_TIME
   */
  @Column(length = 20, columnDefinition = "COMMENT 'The freezed time of record'")
  @Getter
  @Setter
  private String frzDateTime;

  @Column(length = 40, columnDefinition = "COMMENT 'The freezed operator of record'")
  @Getter
  @Setter
  private String frzUserId;
}
