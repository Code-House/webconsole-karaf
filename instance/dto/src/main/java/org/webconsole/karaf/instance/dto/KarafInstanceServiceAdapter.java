package org.webconsole.karaf.instance.dto;

import org.webconsole.karaf.instance.dto.KarafInstanceTO;
import java.util.List;

public interface KarafInstanceServiceAdapter
{
	List<KarafInstanceTO> getInstances();
	
	void startInstance(KarafInstanceTO instance);
	
	void stopInstance(KarafInstanceTO instance);
	
}
