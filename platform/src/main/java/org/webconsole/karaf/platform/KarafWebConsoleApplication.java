package org.webconsole.karaf.platform;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.webconsole.app.WebConsoleApplication;
import org.webconsole.karaf.platform.security.KarafJaasWebSession;
import org.webconsole.ui.pages.HomePage;
import org.webconsole.ui.pages.LoginPage;

public class KarafWebConsoleApplication extends WebConsoleApplication {

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return KarafJaasWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

}
