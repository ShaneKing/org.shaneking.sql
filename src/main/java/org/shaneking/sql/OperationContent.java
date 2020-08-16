package org.shaneking.sql;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NonNull;
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

  public OperationContent appendId(@NonNull String id) {
    return appendIds(Lists.newArrayList(id));
  }

  public OperationContent appendIds(@NonNull List<String> ids) {
    this.setOp(Keyword0.IN);
    List<String> idList = this.getCl();
    if (idList == null) {
      idList = Lists.newArrayList();
      this.setCl(idList);
    }
    for (String id : ids) {
      if (this.getCl().indexOf(id) == -1) {
        this.getCl().add(id);
      }
    }
    return this;
  }

  public OperationContent resetId(@NonNull String id) {
    return this.setOp(String0.EQUAL).setCs(id);
  }

  public OperationContent resetIds(@NonNull List<String> ids) {
    return this.setOp(Keyword0.IN).setCl(ids);
  }

  public OperationContent retainIds(@NonNull List<String> ids) {
    this.setOp(Keyword0.IN);
    List<String> idList = this.getCl();
    if (idList == null || idList.size() < 1) {
      this.setCl(ids);
    } else {
      idList.retainAll(ids);
    }
    return this;
  }
}
