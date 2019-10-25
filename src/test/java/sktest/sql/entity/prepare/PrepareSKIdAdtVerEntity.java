package sktest.sql.entity.prepare;

import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.ling.lang.Integer0;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKIdAdtVerEntity;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKIdAdtVerEntity extends SKIdAdtVerEntity<Map<String, OperationContent>> {


  @Override
  public void groupByStatement(@NonNull List<String> groupByList, @NonNull List<Object> objectList) {
    super.groupByStatement(groupByList, objectList);
    groupByList.add("version");
  }

  @Override
  public void havingStatement(@NonNull List<String> havingList, @NonNull List<Object> objectList) {
    super.havingStatement(havingList, objectList);
    havingList.add("ver > ?");
    objectList.add(Integer0.null2Zero(this.getVer()));
  }

  @Override
  public void orderByStatement(@NonNull List<String> orderByList, @NonNull List<Object> objectList) {
    super.orderByStatement(orderByList, objectList);
    orderByList.add("version");
  }
}
