package sktest.sql.entity.prepare;

import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Table;

@Accessors(chain = true)
@Table
@ToString(callSuper = true)
public class prepareSKAuditEntityTableName extends PrepareSKAuditEntityIdVersion {

}
