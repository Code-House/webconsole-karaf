package org.webconsole.karaf.features.jmx;

import org.code_house.service.api.ServicePointer;
import org.code_house.service.core.AdapterProvider;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class JmxFeaturesServiceAdapter implements AdapterProvider<FeaturesServiceAdapter, MBeanServerConnection> {
    @Override
    public boolean isSupported(Class<?> type) {
        return type.isAssignableFrom(FeaturesServiceAdapter.class);
    }

    @Override
    public Set<ServicePointer<FeaturesServiceAdapter, String>> createAdapter(MBeanServerConnection connection) {
        try {
            Set<ObjectName> objectNames = connection.queryNames(new ObjectName("org.apache.karaf:type=features,*"), null);
            Set<ServicePointer<FeaturesServiceAdapter, String>> pointers = new HashSet<>();
            for (ObjectName objectName : objectNames) {
                pointers.add(new JmxFeaturesPointer(connection, objectName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return null;
    }
}
