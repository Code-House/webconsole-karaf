package org.webconsole.karaf.features.jmx;

import org.apache.karaf.features.management.FeaturesServiceMBean;
import org.code_house.service.api.ServiceLocator;
import org.code_house.service.api.ServicePointer;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

public class JmxFeaturesPointer implements ServicePointer<FeaturesServiceAdapter, String> {

    private MBeanServerConnection connection;
    private ObjectName objectName;

    public JmxFeaturesPointer(MBeanServerConnection connection, ObjectName objectName) {
        this.connection = connection;
        this.objectName = objectName;
    }

    @Override
    public FeaturesServiceAdapter getService() {
        return new JmxFeaturesService(JMX.newMBeanProxy(connection, objectName, FeaturesServiceMBean.class));
    }

    @Override
    public String getServiceId() {
        return null;
    }

    @Override
    public ServiceLocator getServiceLocator() {
        return null;
    }
}
