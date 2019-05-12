package sktest.sql.entity.prepare;

import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;

@Accessors(chain = true)
@ToString(callSuper = true)
@Table
public class PrepareSKAuditEntityColumnNoGetMethod extends PrepareSKAuditEntityIdVersion {
  @Setter
  @Column(length = 10)
  private String noGetMethod;
}
