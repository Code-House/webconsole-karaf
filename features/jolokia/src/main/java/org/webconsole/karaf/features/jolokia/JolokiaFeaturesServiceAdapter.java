package org.webconsole.karaf.features.jolokia;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.code_house.service.api.ServicePointer;
import org.code_house.service.api.WrapperConnection;
import org.code_house.service.core.AdapterProvider;
import org.jolokia.client.J4pClient;
import org.jolokia.client.exception.J4pException;
import org.jolokia.client.request.J4pResponse;
import org.jolokia.client.request.J4pSearchRequest;
import org.json.simple.JSONArray;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;
import org.webconsole.karaf.features.dto.RepositoryTO;

public class JolokiaFeaturesServiceAdapter implements AdapterProvider<FeaturesServiceAdapter, J4pClient> {

    @Override
    public boolean isSupported(Class<?> type) {
        return type.isAssignableFrom(FeaturesServiceAdapter.class);
    }

    @Override
    public boolean isSupported(ServicePointer<?> serviceId) {
        return serviceId instanceof JolokiaFeaturesPointer;
    }

    @Override
    public Set<ServicePointer<FeaturesServiceAdapter>> createAdapters(WrapperConnection<J4pClient> connection) {
        Set<ServicePointer<FeaturesServiceAdapter>> pointers = new HashSet<>();
        try {
            J4pResponse<J4pSearchRequest> response = connection.getConnection().execute(new J4pSearchRequest("org.apache.karaf:type=features,*"));
            JSONArray found = response.getValue();
            for (Object o : found) {
                pointers.add(new JolokiaFeaturesPointer(connection, new ObjectName(o.toString())));
            }
        } catch (J4pException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return pointers;
    }

    @Override
    public ServicePointer<FeaturesServiceAdapter> createAdapter(ServicePointer<FeaturesServiceAdapter> identifier, WrapperConnection<J4pClient> connection) {
        try {
            return new JolokiaFeaturesPointer(connection, new ObjectName("org.apache.karaf:type=features,name=" + identifier.getProperties().get("InstanceName")));
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JolokiaFeaturesServiceAdapter adapter = new JolokiaFeaturesServiceAdapter();

        Set<ServicePointer<FeaturesServiceAdapter>> pointers = adapter.createAdapters(new WrapperConnection<J4pClient>("0000", new J4pClient("http://localhost:8040/jolokia")));
        for (ServicePointer<FeaturesServiceAdapter> pointer : pointers) {
            FeaturesServiceAdapter service = pointer.createService();
            System.out.printf("Pointer " + pointer.getProperties() + " " + service);

            List<RepositoryTO> repositories = service.getRepositories();
            System.out.println(repositories);
        }
    }

}
