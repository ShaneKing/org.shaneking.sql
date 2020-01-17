package sktest.sql.entity;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PrepareInitTest.class, PrepareSetterToStringTest.class, PrepareSqlMockTest.class, PrepareSqlParameterizedTest.class, PrepareSqlTest.class, PrepareTest.class})
public class EntitySuites {
}
