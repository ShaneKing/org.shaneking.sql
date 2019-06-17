package org.shaneking.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class PageHelper {

  @Getter
  @Setter
  private Integer count;

  @Getter
  @Setter
  private Integer limit;

  @Getter
  @Setter
  private Integer offset;
}
