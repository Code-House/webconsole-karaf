package org.webconsole.karaf.branding.internal;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.webconsole.api.BrandProvider;

public class KarafBrandActivator implements BundleActivator {

    private ServiceRegistration<BrandProvider> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        registration = context.registerService(BrandProvider.class, new KarafBrandProvider(), new Hashtable<String, Object>());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (registration != null) {
            registration.unregister();
        }
    }

}
