package sktest.sql.entity.prepare;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKRefAdtVerFullEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKRefAdtVerFullEntity extends SKRefAdtVerFullEntity<Map<String, OperationContent>> {

  @Column(nullable = false)
  @Getter
  private Integer intForMapRowException;
}
