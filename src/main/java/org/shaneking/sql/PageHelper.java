package org.shaneking.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.ling.lang.Integer0;

@Accessors(chain = true)
@ToString
public class PageHelper {
  public static int DEFAULT_LIMIT = 100;

  @Setter
  private Integer limit;

  @Getter
  @Setter
  private Integer offset;

  public Integer getLimit() {
    return Integer0.null2Default(this.limit, DEFAULT_LIMIT);
  }
}
