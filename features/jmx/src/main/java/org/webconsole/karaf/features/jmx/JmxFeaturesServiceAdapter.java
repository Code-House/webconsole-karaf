package org.webconsole.karaf.features.jmx;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.code_house.service.api.ServicePointer;
import org.code_house.service.api.WrapperConnection;
import org.code_house.service.core.AdapterProvider;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;

/**
 */
public class JmxFeaturesServiceAdapter implements AdapterProvider<FeaturesServiceAdapter, MBeanServerConnection> {

    @Override
    public boolean isSupported(Class<?> type) {
        return type.isAssignableFrom(FeaturesServiceAdapter.class);
    }

    @Override
    public boolean isSupported(ServicePointer<?> serviceId) {
        return JmxFeaturesPointer.class.isAssignableFrom(serviceId.getClass());
    }

    @Override
    public Set<ServicePointer<FeaturesServiceAdapter>> createAdapters(WrapperConnection<MBeanServerConnection> connection) {
        try {
            Set<ObjectName> objectNames = connection.getConnection().queryNames(new ObjectName("org.apache.karaf:type=features,*"), null);
            Set<ServicePointer<FeaturesServiceAdapter>> pointers = new HashSet<>();
            for (ObjectName objectName : objectNames) {
                pointers.add(new JmxFeaturesPointer(connection, objectName));
            }
            return pointers;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ServicePointer<FeaturesServiceAdapter> createAdapter(ServicePointer<FeaturesServiceAdapter> identifier, WrapperConnection<MBeanServerConnection> connection) {
        try {
            Object instanceName = identifier.getProperties().get("InstanceName");
            return new JmxFeaturesPointer(connection, new ObjectName("org.apache.karaf:type=features,name=" + instanceName));
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        JMXServiceURL serviceURL = new JMXServiceURL("service:jmx:rmi://127.0.0.1:44444/jndi/rmi://127.0.0.1:1099/karaf-trun");

        Hashtable<String, Object> environment = new Hashtable<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[] {"karaf", "karaf"});
        JMXConnector connector = JMXConnectorFactory.newJMXConnector(serviceURL, environment);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        WrapperConnection<MBeanServerConnection> wrapperConnection = new WrapperConnection<>("0000", connection);

        JmxFeaturesServiceAdapter adapter = new JmxFeaturesServiceAdapter();
        Set<ServicePointer<FeaturesServiceAdapter>> adapters = adapter.createAdapters(wrapperConnection);

        for (ServicePointer<FeaturesServiceAdapter> servicePointer : adapters) {
            FeaturesServiceAdapter service = servicePointer.createService();
            System.out.println("-->" + service);

            System.out.println(service.getRepositories());
            System.out.println(service.getFeatures());

            ServicePointer<FeaturesServiceAdapter> pointer = adapter.createAdapter(servicePointer, wrapperConnection);
            service = pointer.createService();
            System.out.println("-->" + service);

            System.out.println(service.getRepositories());
            System.out.println(service.getFeatures());
        }

    }

}
