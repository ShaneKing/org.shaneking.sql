package sktest.sql.prepare;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKEntity;

import javax.persistence.Table;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString
public class PrepareSKEntityPageHelper extends SKEntity<Map<String, OperationContent>> {
}
