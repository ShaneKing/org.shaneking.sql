package sktest.sql;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.shaneking.jackson.databind.OM3;
import org.shaneking.sql.OperationContent;

public class OperationContentTest extends SKUnit {
  @Test
  public void setter() {
    Assert.assertEquals("{\"le\":\"le\",\"op\":\"op\",\"cs\":\"cs\",\"bw\":\"bw\",\"ew\":\"ed\"}", OM3.writeValueAsString(new OperationContent().setLe("le").setOp("op").setBw("bw").setCl(Lists.newArrayList()).setCs("cs").setEw("ed")));
  }

  @Test
  public void appendId() {
    Assert.assertEquals("{\"op\":\"in\",\"cl\":[\"id\"]}", OM3.writeValueAsString(new OperationContent().appendId("id")));
  }

  @Test
  public void appendIds() {
    Assert.assertEquals("{\"op\":\"in\",\"cl\":[\"id\"]}", OM3.writeValueAsString(new OperationContent().appendIds(Lists.newArrayList("id"))));
  }

  @Test
  public void resetId() {
    Assert.assertEquals("{\"op\":\"=\",\"cs\":\"id\"}", OM3.writeValueAsString(new OperationContent().resetId("id")));
  }

  @Test
  public void resetIds() {
    Assert.assertEquals("{\"op\":\"in\",\"cl\":[\"id\"]}", OM3.writeValueAsString(new OperationContent().resetIds(Lists.newArrayList("id"))));
  }

  @Test
  public void retainIds() {
    Assert.assertEquals("{\"op\":\"in\",\"cl\":[\"a\"]}", OM3.writeValueAsString(new OperationContent().setCl(Lists.newArrayList("a", "1")).retainIds(Lists.newArrayList("a", "2"))));
  }

  @Test
  public void retainIdsEmpty() {
    Assert.assertEquals("{\"op\":\"in\",\"cl\":[\"id\"]}", OM3.writeValueAsString(new OperationContent().retainIds(Lists.newArrayList("id"))));
  }

  @Test
  public void testToString() {
    Assert.assertEquals("OperationContent(le=null, op=null, cl=null, cs=null, bw=null, ew=null)", new OperationContent().toString());
  }
}
