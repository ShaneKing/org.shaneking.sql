package sktest.sql.entity;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EntityInitTest.class, EntitySetterToStringTest.class, EntityMapRowTest.class, EntitySqlTest.class, EntityFillOcTest.class, EntityFullTableNameTest.class})
public class EntitySuites {
}
