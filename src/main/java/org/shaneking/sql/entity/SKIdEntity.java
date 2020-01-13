package org.shaneking.sql.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.lang.String0;

import javax.persistence.Column;
import javax.persistence.Id;

@Accessors(chain = true)
@ToString
public abstract class SKIdEntity<J> extends SKEntity<J> {
  public static final String FIELD__ID = "id";

  //System.currentTimeMillis()-UUID0.l19()=13+1+19=33=UUID0.cMl33()
  //Date.now()-SK.l22()=13+1+22=36=SK.cMl40()
  //Date0.on().datetimes()-UUID0.l19()=17+1+19=37=UUID0.dMl37()
  //Date0.on().datetimes()-SK.l22()=17+1+22=40=SK.cMl40()
  //Date0.on().datetimes()_UUID.randomUUID()=17+1+36=54
  @Column(length = 40, updatable = false, columnDefinition = "COMMENT 'Uniquely identifies'")
  @Getter
  @Id
  @Setter
  private String id;

  public SKIdEntity<J> initId(@NonNull String id) {
    return this.setId(String0.null2empty2(this.getId(), id));
  }
}
