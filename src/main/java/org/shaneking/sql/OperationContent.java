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

  public void appendId(@NonNull String id) {
    this.setOp(Keyword0.IN);
    List<String> idList = this.getCl();
    if (idList == null) {
      idList = Lists.newArrayList();
      this.setCl(idList);
    }
    if (this.getCl().indexOf(id) == -1) {
      this.getCl().add(id);
    }
  }

  public void appendIds(@NonNull List<String> ids) {
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
  }

  public void resetId(@NonNull String id) {
    this.setOp(String0.EQUAL).setCs(id);
  }

  public void resetIds(@NonNull List<String> ids) {
    this.setOp(Keyword0.IN).setCl(ids);
  }

  public void retainIds(@NonNull List<String> ids) {
    this.setOp(Keyword0.IN);
    List<String> idList = this.getCl();
    if (idList == null) {
      this.setCl(ids);
    } else {
      idList.retainAll(ids);
    }
  }
}
