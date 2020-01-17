package sktest.sql.entity.prepare;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKIdEntity;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKIdEntity extends SKIdEntity<Map<String, OperationContent>> {
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
}
