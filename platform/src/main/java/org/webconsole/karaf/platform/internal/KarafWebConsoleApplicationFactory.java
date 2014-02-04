package org.webconsole.karaf.platform.internal;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.webconsole.karaf.platform.KarafWebConsoleApplication;

public class KarafWebConsoleApplicationFactory implements IWebApplicationFactory {

    @Override
    public WebApplication createApplication(WicketFilter filter) {
        return new KarafWebConsoleApplication();
    }

    @Override
    public void destroy(WicketFilter filter) {
    }

}
