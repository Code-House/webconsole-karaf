package org.webconsole.karaf.instance.jmx;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.apache.karaf.admin.management.AdminServiceMBean;
import org.code_house.service.api.WrapperConnection;
import org.code_house.service.jmx.JmxServicePointer;
import org.webconsole.karaf.instance.dto.KarafInstanceServiceAdapter;

public class JmxKarafInstancePointer extends JmxServicePointer<KarafInstanceServiceAdapter>
{

	private Map<String, Object> properties = new HashMap<>();

	protected JmxKarafInstancePointer(WrapperConnection<MBeanServerConnection> connection, ObjectName objectName)
	{
		super(connection, objectName);
		properties.put("InstanceName", objectName.getKeyProperty("name"));
	}

	@Override
	public KarafInstanceServiceAdapter createService()
	{
		return new JmxKarafInstanceService(createProxy(AdminServiceMBean.class));
	}

	@Override
	public Map<String, Object> getProperties()
	{
		return properties;
	}

}
