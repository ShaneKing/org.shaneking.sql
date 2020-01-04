package sktest.sql.entity.prepare;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKZoneAdtVerEntity;

import javax.persistence.Table;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString
public class PrepareSKZoneAdtVerEntity extends SKZoneAdtVerEntity<Map<String, OperationContent>> {
}
