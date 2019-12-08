package sktest.sql;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sktest.sql.entity.EntitySuites;

@RunWith(Suite.class)
@Suite.SuiteClasses({EntitySuites.class, Keyword0Test.class, OperationContentTest.class, PageHelperTest.class})
public class SqlSuites {
}
