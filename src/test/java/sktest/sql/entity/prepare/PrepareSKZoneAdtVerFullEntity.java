package sktest.sql.entity.prepare;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKZoneAdtVerFullEntity;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString
public class PrepareSKZoneAdtVerFullEntity extends SKZoneAdtVerFullEntity<Map<String, OperationContent>> {
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
