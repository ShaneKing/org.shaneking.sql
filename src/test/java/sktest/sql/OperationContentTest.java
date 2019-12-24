package sktest.sql;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.shaneking.sql.OperationContent;

public class OperationContentTest extends SKUnit {
  @Test
  public void setter() {
    skPrint(new OperationContent().setLe("le").setOp("op").setBw("bw").setCl(Lists.newArrayList()).setCs("cs").setEw("ed"));
  }
}
