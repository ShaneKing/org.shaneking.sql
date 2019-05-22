package sktest.sql;

import org.junit.Test;
import org.shaneking.sql.OperationContent;

public class OperationContentTest extends SKUnit {
  @Test
  public void setter() {
    skPrint(new OperationContent().setBw("").setCs("").setEw("").setOp(""));
  }

}
