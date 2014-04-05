package org.webconsole.karaf.features.ui;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.webconsole.karaf.features.dto.FeatureTO;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;

public class InstallationPanel extends Panel
{

	public InstallationPanel(String id, IModel<FeatureTO> rowModel, FeaturesServiceAdapter adapter)
	{
		super(id);
		
		Link link;
		final IModel<FeatureTO> feature = rowModel;
		final FeaturesServiceAdapter tmpAdapter = adapter;
		
		if(rowModel.getObject().isInstalled())
		{
			link = new Link("link")
			{
				@Override
				public void onClick()
				{
					tmpAdapter.uninstallFeature(feature.getObject());
				}
				
			};
			Label label = new Label("label");
			label.add(new AttributeModifier("class", "glyphicon glyphicon-stop"));
			link.add(label);
		}
		else
		{
			link = new Link("link")
			{
				@Override
				public void onClick()
				{
					tmpAdapter.installFeature(feature.getObject());
				}
				
			};
			Label label = new Label("label");
			label.add(new AttributeModifier("class", "glyphicon glyphicon-play"));
			link.add(label);
		}
		add(link);
		
	}

}
