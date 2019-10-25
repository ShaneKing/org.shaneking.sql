package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.ling.lang.String0;

import javax.persistence.Column;
import javax.persistence.Id;

@Accessors(chain = true)
@ToString(callSuper = true)
public class SKIdEntity<J> extends SKEntity<J> {

  //14+1+19=34 ~ 14+1+36=51
  @Column(length = 51, updatable = false, columnDefinition = "COMMENT 'Uniquely identifies'")
  @Getter
  @Id
  @Setter
  private String id;//yyyyMMddHHmmss_uuid

  public SKIdEntity<J> initId(@NonNull String id) {
    return this.setId(String0.null2empty2(this.getId(), id));
  }
}
