package org.shaneking.sql;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.lang.String0;

import java.util.List;

@Accessors(chain = true)
@ToString
public class OperationContent {
  //will trans in request and response, so abbreviation
  @Getter
  @Setter
  private String le;//left expr
  @Getter
  @Setter
  private String op;//between,in,like,>...
  @Getter
  @Setter
  private List<String> cl;//content List
  @Getter
  @Setter
  private String cs;//content String
  @Getter
  @Setter
  private String bw;//beginWith
  @Getter
  @Setter
  private String ew;//endWith

  public void forceSomeId(String id) {
    if (Keyword0.IN.equalsIgnoreCase(this.getOp())) {
      if (this.getCl() == null) {
        this.setCl(Lists.newArrayList());
      }
      this.getCl().add(id);
    } else {
      this.setOp(String0.EQUAL);
      this.setCs(id);
    }
  }

  public void forceSomeIds(List<String> ids) {
    this.setOp(Keyword0.IN);
    if (this.getCl() == null) {
      this.setCl(Lists.newArrayList());
    }
    this.getCl().addAll(ids);
  }
}
