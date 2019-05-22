package sktest.sql.entity.prepare;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.entity.SKAuditEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Version;

@Accessors(chain = true)
@ToString(callSuper = true)
public class PrepareSKAuditEntityIdVersion extends SKAuditEntity {

  @Column(length = 40, updatable = false)
  @Getter
  @Id
  @Setter
  private String id;

  @Column(length = 20)
  @Getter
  @Setter
  @Version
  private Integer version = 1;
}
