package sktest.sql.entity.prepare;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKL10nAdtVerFullEntity;

import javax.persistence.Table;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString
public class PrepareSKL10nAdtVerFullEntity extends SKL10nAdtVerFullEntity<Map<String, OperationContent>> {
}
