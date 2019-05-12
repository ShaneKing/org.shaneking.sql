package sktest.sql.entity.prepare;

import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Table;

@Accessors(chain = true)
@ToString(callSuper = true)
@Table
public class prepareSKAuditEntityTableName extends PrepareSKAuditEntityIdVersion {

}
