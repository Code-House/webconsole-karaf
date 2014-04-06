package org.webconsole.karaf.instance.ui;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.code_house.service.api.ServicePointer;
import org.webconsole.karaf.instance.dto.KarafInstanceServiceAdapter;
import org.webconsole.karaf.instance.dto.KarafInstanceTO;

public class InstallationPanel extends Panel
{

	public InstallationPanel(String id, IModel<ServicePointer<KarafInstanceServiceAdapter>> rowModel)
	{
		super(id);
//
//		final IModel<ServicePointer<KarafInstanceServiceAdapter>>feature = rowModel;
//		KarafInstanceServiceAdapter adapter = rowModel.getObject().createService();
//		
//		List<KarafInstanceTO> features = adapter.getInstances();
//		
//		Link link;
//		
		
	}

}
