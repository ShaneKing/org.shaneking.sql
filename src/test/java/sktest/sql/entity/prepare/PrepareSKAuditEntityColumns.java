package sktest.sql.entity.prepare;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKAuditEntityColumns extends PrepareSKAuditEntityIdVersion {
  @Getter
  @Setter
  private String withoutAnnotation;

  @Column(length = 10)
  @Getter
  @Setter
  private String hasLength;

  @Column(name = "re_name")
  @Getter
  @Setter
  private String reName;
}
