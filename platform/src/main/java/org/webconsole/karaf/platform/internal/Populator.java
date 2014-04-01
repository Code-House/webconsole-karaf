package org.webconsole.karaf.platform.internal;

import java.util.Hashtable;
import java.util.List;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;

import org.code_house.service.api.ServiceLocator;
import org.code_house.service.core.AdapterProvider;
import org.code_house.service.jmx.JmxConnectionManager;
import org.code_house.service.jmx.JmxServiceLocator;

public class Populator {

    public static ServiceLocator locator(List<AdapterProvider<?, MBeanServerConnection>> adapterProviders) {
        JmxConnectionManager connectionManager = new JmxConnectionManager();

        Hashtable<String, Object> environment = new Hashtable<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[] {"karaf", "karaf"});

        connectionManager.addConnection("service:jmx:rmi://127.0.0.1:44444/jndi/rmi://127.0.0.1:1099/karaf-root", environment);
        connectionManager.addConnection("service:jmx:rmi://127.0.0.1:44449/jndi/rmi://127.0.0.1:1104/karaf-wicket", environment);
        return new JmxServiceLocator(connectionManager, adapterProviders);
    }

}
