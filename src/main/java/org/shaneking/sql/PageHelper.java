package org.shaneking.sql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ToString
public class PageHelper {
  public static int DEFAULT_LIMIT = 30;
  public static int MAX_LIMIT = 1300;// table has 1013 column in some company

  @Getter
  @Setter
  private Integer limit;//db limit (ui records of page), for request

  @Getter
  @Setter
  private Integer offset;//db offset, calc
}
