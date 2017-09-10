package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	test.ParseXMLInterfaceTest.class,
	test.parser.ArticleParserTest.class,
	test.parser.CommitteeParserTest.class,
	test.parser.InproceedingParserTest.class,
	test.parser.MasterThesisParserTest.class,
	test.parser.PhdThesisParserTest.class,
	test.parser.wwwParserTest.class,
	test.queryengine.CommitteeQueryTest.class,
	test.queryengine.QueryBuilderTest.class,
	test.queryengine.SystemUserQueryTest.class,
	test.ui.testSearchResultsCommitteeListUI.class,
	test.ui.testLoginUI.class,
	test.ui.testLogoutUI.class,
	test.ui.testMainUI.class,
	test.ui.testRegisterUI.class,
	test.ui.testUpdateProfileUI.class,
	test.ui.testSearchResultsCommitteeListUI.class,
	test.ui.testUpdateProfileUI.class
})


public class JUnitTestSuite {

}
