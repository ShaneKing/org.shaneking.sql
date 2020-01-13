package sktest.sql.entity.prepare;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.lang.Integer0;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKIdAdtVerEntity;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString
public class PrepareSKIdAdtVerEntity extends SKIdAdtVerEntity<Map<String, OperationContent>> {
  public List<OperationContent> findHavingOCs(@NonNull String fieldName) {
    List<OperationContent> rtnList = Lists.newArrayList();
    //implements by sub entity
    return rtnList;
  }

  public List<OperationContent> findWhereOCs(@NonNull String fieldName) {
    List<OperationContent> rtnList = Lists.newArrayList();
    //implements by sub entity
    return rtnList;
  }


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
