package com.msd.testsuite;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.msd.queryengine.AuthorBackgroundDetailsQueryTest;
import com.msd.queryengine.CommitteeQueryTest;
import com.msd.queryengine.PublicationListQueryTest;
import com.msd.queryengine.QueryBuilderTest;
import com.msd.queryengine.SystemUserQueryTest;
import com.msd.queryengine.ViewAuthorDetailsTest;
import com.msd.userinterface.LoginUITest;
import com.msd.userinterface.LogoutUITest;
import com.msd.userinterface.MainUITest;
import com.msd.userinterface.RegisterUITest;
import com.msd.userinterface.SearchResultsCommitteeListUITest;
import com.msd.userinterface.SearchUITest;
import com.msd.userinterface.UpdateProfileUITest;

@RunWith(Suite.class)
@SuiteClasses({ AuthorBackgroundDetailsQueryTest.class, CommitteeQueryTest.class, PublicationListQueryTest.class,
		QueryBuilderTest.class, SystemUserQueryTest.class, ViewAuthorDetailsTest.class, LoginUITest.class, LogoutUITest.class, MainUITest.class, RegisterUITest.class,
		SearchResultsCommitteeListUITest.class, SearchUITest.class, UpdateProfileUITest.class })
public class JUnitTestSuite {

}
