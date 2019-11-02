package org.shaneking.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class PageHelper {
  public static int DEFAULT_LIMIT = 30;
  public static int MAX_LIMIT = 300;

  @Getter
  @Setter
  private Integer limit;

  @Getter
  @Setter
  private Integer offset;
}
