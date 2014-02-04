package org.webconsole.karaf.platform.internal;

import java.util.Hashtable;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class KarafWebConsoleActivator implements BundleActivator {

    private ServiceRegistration<IWebApplicationFactory> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        Hashtable<String, Object> properties = new Hashtable<String, Object>();
        properties.put("mount-point", "/webconsole");
        properties.put("application-name", "Karaf WebConsole");

        registration = context.registerService(
            IWebApplicationFactory.class,
            new KarafWebConsoleApplicationFactory(),
            properties
        );
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (registration != null) {
            registration.unregister();
        }
    }

}
