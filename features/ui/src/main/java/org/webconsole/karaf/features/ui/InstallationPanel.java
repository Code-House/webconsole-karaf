package org.webconsole.karaf.features.ui;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class InstallationPanel extends Panel
{

	public InstallationPanel(String id)
	{
		super(id);
		
		Link link = new Link("link")
		{
			
			@Override
			public void onClick()
			{
				System.out.println("link:onClick()");
			}
			
		};
		Label label = new Label("label");
		label.add(new AttributeModifier("class", "icon-play"));
		link.add(label);
		add(link);
	}

}
