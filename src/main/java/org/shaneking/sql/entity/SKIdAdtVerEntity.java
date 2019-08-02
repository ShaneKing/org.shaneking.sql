package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.ling.lang.Integer0;

import javax.persistence.Column;
import javax.persistence.Version;

@Accessors(chain = true)
@ToString(callSuper = true)
public class SKIdAdtVerEntity<J> extends SKIdAdtEntity<J> {

  @Column(nullable = false, columnDefinition = "COMMENT 'Version for optimistic locking'")
  @Getter
  @Setter
  @Version
  private Integer version;

  public SKIdAdtVerEntity<J> initVersion() {
    return this.setVersion(Integer0.null2Zero(this.getVersion()));
  }

  public SKIdAdtVerEntity<J> initWithUserId(@NonNull String userId) {
    super.initWithUserId(userId);
    return this.initVersion();
  }

  public SKIdAdtVerEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}