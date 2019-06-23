package sktest.sql.entity.prepare;

import lombok.ToString;
import lombok.experimental.Accessors;
import org.shaneking.sql.OperationContent;
import org.shaneking.sql.entity.SKL10nEntity;

import javax.persistence.Table;
import java.util.Map;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class PrepareSKL10nEntity extends SKL10nEntity<Map<String, OperationContent>> {
}
