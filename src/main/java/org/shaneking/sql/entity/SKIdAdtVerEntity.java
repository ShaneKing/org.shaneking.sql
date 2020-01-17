package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.lang.Integer0;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.Version;

@Accessors(chain = true)
@ToString
public abstract class SKIdAdtVerEntity<J> extends SKIdAdtEntity<J> {
  @Transient
  public static final String FIELD__VER = "ver";

  @Column(nullable = false, columnDefinition = "COMMENT 'Version for optimistic locking'")
  @Getter
  @Setter
  @Version
  private Integer ver;

  public SKIdAdtVerEntity<J> initVer() {
    return this.setVer(Integer0.null2Zero(this.getVer()));
  }

  @Override
  public SKIdAdtVerEntity<J> initWithUserId(@NonNull String userId) {
    super.initWithUserId(userId);
    return this.initVer();
  }

  @Override
  public SKIdAdtVerEntity<J> initWithUserIdAndId(@NonNull String userId, @NonNull String id) {
    this.initId(id);
    return this.initWithUserId(userId);
  }
}
