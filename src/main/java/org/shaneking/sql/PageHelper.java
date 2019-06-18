package org.shaneking.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
    return limit == null ? DEFAULT_LIMIT : limit;
  }
}
