package org.webconsole.karaf.instance.jmx;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.code_house.service.api.ServicePointer;
import org.code_house.service.api.WrapperConnection;
import org.code_house.service.core.AdapterProvider;
import org.webconsole.karaf.instance.dto.KarafInstanceServiceAdapter;

public class JmxKarafInstanceAdapter implements AdapterProvider<KarafInstanceServiceAdapter, MBeanServerConnection>
{

	@Override
	public boolean isSupported(Class<?> type)
	{
		return type.isAssignableFrom(KarafInstanceServiceAdapter.class);
	}

	@Override
	public boolean isSupported(ServicePointer<?> serviceId)
	{
		return JmxKarafInstancePointer.class.isAssignableFrom(serviceId.getClass());
	}

	@Override
	public Set<ServicePointer<KarafInstanceServiceAdapter>> createAdapters(WrapperConnection<MBeanServerConnection> connection)
	{
		try
		{
			Set<ObjectName> objectNames = connection.getConnection().queryNames(new ObjectName("org.apache.karaf:type=admin,*"), null);
			Set<ServicePointer<KarafInstanceServiceAdapter>> pointers = new HashSet<>();
			for (ObjectName objectName : objectNames)
			{
				pointers.add(new JmxKarafInstancePointer(connection, objectName));
			}
			return pointers;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (MalformedObjectNameException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ServicePointer<KarafInstanceServiceAdapter> createAdapter(ServicePointer<KarafInstanceServiceAdapter> identifier,
			WrapperConnection<MBeanServerConnection> connection)
	{
		try
		{
			Object instanceName = identifier.getProperties().get("InstanceName");
			return new JmxKarafInstancePointer(connection, new ObjectName("org.apache.karaf:type=admin,name=" + instanceName));
		}
		catch (MalformedObjectNameException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
