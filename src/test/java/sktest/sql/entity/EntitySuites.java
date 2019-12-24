package sktest.sql.entity;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SKEntityInitTest.class, SKEntitySetterTest.class, SKEntityTest.class, SKIdAdtVerEntityTest.class, SKIdAdtVerFullEntityTest.class, SKRefAdtVerFullEntityTest.class})
public class EntitySuites {
}
