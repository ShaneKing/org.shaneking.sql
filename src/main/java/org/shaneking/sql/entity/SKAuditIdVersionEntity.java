package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Version;

@Accessors(chain = true)
@ToString(callSuper = true)
public class SKAuditIdVersionEntity<J> extends SKAuditEntity<J> {

  @Column(length = 40)
  @Getter
  @Id
  @Setter
  private String id;

  @Column(nullable = false)
  @Getter
  @Setter
  @Version
  private Integer version;
}
