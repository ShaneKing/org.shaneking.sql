package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * Just reference, used for upload file
 */
@Accessors(chain = true)
@ToString
public abstract class SKRefIdEntity<J> extends SKIdEntity<J> {
  @Transient
  public static final String FIELD__REF_ID = "refId";
  @Transient
  public static final String FIELD__REF_TYPE = "refType";

  @Column(length = 40, columnDefinition = "COMMENT 'Relation unique flag'")
  @Getter
  @Setter
  private String refId;

  @Column(length = 40, columnDefinition = "COMMENT 'Relation type'")
  @Getter
  @Setter
  private String refType;
}
