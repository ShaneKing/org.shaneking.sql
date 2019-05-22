package sktest.sql.entity.prepare;

import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKAuditEntityColumnNoGetMethod extends PrepareSKAuditEntityIdVersion {
  @Column(length = 10)
  @Setter
  private String noGetMethod;
}
