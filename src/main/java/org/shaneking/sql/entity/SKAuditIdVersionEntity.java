package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.ling.lang.Integer0;
import org.shaneking.skava.ling.lang.String0;
import org.shaneking.skava.ling.util.UUID0;

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

  public SKAuditIdVersionEntity<J> initWithCreateUserId(@NonNull String createUserId) {
    return this.initWithCreateUserIdAndId(createUserId, UUID0.l19());
  }

  public SKAuditIdVersionEntity<J> initWithCreateUserIdAndId(@NonNull String createUserId, @NonNull String id) {
    super.initWithCreateUserId(createUserId);
    return this.setId(String0.null2empty2(this.getId(), id)).setVersion(Integer0.null2Zero(this.getVersion()));
  }
}
