package sktest.sql.entity.prepare;

import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.skava.ling.lang.Integer0;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKAuditIdVersionEntity;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKAuditIdVersionEntity extends SKAuditIdVersionEntity<Map<String, OperationContent>> {


  @Override
  public void groupByStatementExt(@NonNull List groupByList, @NonNull List objectList) {
    groupByList.add("version");
  }

  @Override
  public void havingStatementExt(@NonNull List havingList, @NonNull List list) {
    havingList.add("version > ?");
    list.add(Integer0.null2Zero(this.getVersion()));
  }

  @Override
  public void orderByStatementExt(@NonNull List orderByList, @NonNull List list) {
    orderByList.add("version");
  }
}
