package sktest.sql.entity.prepare;

import com.google.common.collect.Lists;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.lang.String0;
import org.shaneking.sql.Keyword0;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKIdAdtVerFullEntity;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString
public class PrepareSKIdAdtVerFullEntity extends SKIdAdtVerFullEntity<Map<String, OperationContent>> {

  @Override
  public List<OperationContent> findWhereOCs(String fieldName) {
    if ("crtUserId".equals(fieldName)) {
      return Lists.newArrayList(new OperationContent().setOp(Keyword0.IN).setCl(Lists.newArrayList("1", "a", ",")));
    } else if ("crtDateTime".equals(fieldName)) {
      return Lists.newArrayList(new OperationContent().setOp(Keyword0.BETWEEN).setCl(Lists.newArrayList("1949-10-01")));
    } else if ("ivdDateTime".equals(fieldName)) {
      return Lists.newArrayList(new OperationContent().setOp(Keyword0.BETWEEN).setCl(Lists.newArrayList("1949-10-01", "1996-07")));
    } else if ("modDateTime".equals(fieldName)) {
      return Lists.newArrayList(new OperationContent().setBw(String0.PERCENT).setCs("1949-10-01").setEw(String0.PERCENT).setOp("like"));
    }
    return super.findWhereOCs(fieldName);
  }
}
