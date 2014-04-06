package org.webconsole.karaf.instance.jmx;

import java.util.Collections;
import java.util.List;

import javax.management.openmbean.CompositeData;

import org.apache.karaf.admin.management.AdminServiceMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webconsole.karaf.instance.jmx.ts.TabularFunction;
import org.webconsole.karaf.instance.jmx.ts.TabularStructures;
import org.webconsole.karaf.instance.dto.KarafInstanceServiceAdapter;
import org.webconsole.karaf.instance.dto.KarafInstanceTO;

public class JmxKarafInstanceService implements KarafInstanceServiceAdapter
{

	private final Logger logger = LoggerFactory.getLogger(JmxKarafInstanceService.class);
	private final AdminServiceMBean adminService;

	public JmxKarafInstanceService(AdminServiceMBean adminService)
	{
		this.adminService = adminService;
	}

	@Override
	public List<KarafInstanceTO> getInstances()
	{
		try
		{
			return TabularStructures.apply(adminService.getInstances(), new TabularFunction<String, KarafInstanceTO>()
			{
				@Override
				public KarafInstanceTO apply(List<String> key, CompositeData value)
				{
					String name = (String) value.get(AdminServiceMBean.INSTANCE_NAME);
					Integer pid = (Integer) value.get(AdminServiceMBean.INSTANCE_PID);
					String state = (String) value.get(AdminServiceMBean.INSTANCE_STATE);
					return new KarafInstanceTO(name, pid, state);
				}
			});
		}
		catch (Exception e)
		{
			logger.error("Unable to fetch features", e);
		}

		return Collections.emptyList();
	}

	@Override
	public void startInstance(KarafInstanceTO instance)
	{
		try
		{
			adminService.startInstance(instance.getName());
		}
		catch (Exception e)
		{
			logger.error("Failed to start karaf instance {}", instance.getName(), e);
		}

	}

	@Override
	public void stopInstance(KarafInstanceTO instance)
	{
		try
		{
			adminService.stopInstance(instance.getName());
		}
		catch (Exception e)
		{
			logger.error("Failed to stop karaf instance {}", instance.getName(), e);
		}
	}

}
