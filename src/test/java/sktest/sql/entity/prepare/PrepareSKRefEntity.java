package sktest.sql.entity.prepare;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKRefEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKRefEntity extends SKRefEntity<Map<String, OperationContent>> {

  @Column(nullable = false)
  @Getter
  private Integer intForMapRowException;
}
