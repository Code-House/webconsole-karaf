/*
 * Copyright (C) 2014 Code-House, Lukasz Dywicki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.webconsole.karaf.features.jolokia;

import java.util.HashMap;
import java.util.Map;

import javax.management.ObjectName;

import org.apache.karaf.features.management.FeaturesServiceMBean;
import org.code_house.service.api.ServicePointer;
import org.code_house.service.api.WrapperConnection;
import org.code_house.service.jolokia.Jolokia;
import org.jolokia.client.J4pClient;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;
import org.webconsole.karaf.features.jmx.JmxFeaturesService;

/**
 * Created by lukasz on 13.02.2014.
 */
public class JolokiaFeaturesPointer implements ServicePointer<FeaturesServiceAdapter> {

    private final WrapperConnection<J4pClient> client;
    private final ObjectName objectName;
    private final String connectionId;
    private Map<String, Object> properties = new HashMap<>();

    public JolokiaFeaturesPointer(WrapperConnection<J4pClient> client, ObjectName objectName) {
        this.client = client;
        this.objectName = objectName;
        this.connectionId = client.getConnectionId();
        properties.put("InstanceName", objectName.getKeyProperty("name"));
    }

    @Override
    public FeaturesServiceAdapter createService() {
        return new JmxFeaturesService(Jolokia.newMBeanProxy(client.getConnection(), objectName, FeaturesServiceMBean.class));
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public boolean isBoundTo(WrapperConnection<?> connection) {
        return connection.getConnection() instanceof J4pClient && connection.getConnectionId().equals(connectionId);
    }

}
