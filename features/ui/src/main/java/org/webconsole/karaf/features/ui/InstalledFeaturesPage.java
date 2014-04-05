package org.webconsole.karaf.features.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.tree.TreeNode;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.tree.table.IRenderable;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.code_house.service.api.ServicePointer;
import org.webconsole.karaf.features.dto.FeatureTO;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;
import org.webconsole.ui.pages.SecuredPage;

public class InstalledFeaturesPage extends SecuredPage
{
	public InstalledFeaturesPage(ServicePointer<FeaturesServiceAdapter> servicePointer)
	{
		final FeaturesServiceAdapter adapter = servicePointer.createService();
		List<FeatureTO> features = adapter.getFeatures();

        List<IColumn<FeatureTO, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<FeatureTO, String>(Model.of("nazwa"), "name"));
        columns.add(new PropertyColumn<FeatureTO, String>(Model.of("wersja"), "version"));
        columns.add(new AbstractColumn<FeatureTO, String>(Model.of("przycisiki"))
		{
			@Override
			public void populateItem(Item<ICellPopulator<FeatureTO>> cellItem,
					String componentId, IModel<FeatureTO> rowModel)
			{
				InstallationPanel installationPanel = new InstallationPanel(componentId, rowModel, adapter);
				cellItem.add(installationPanel);
			}
        	
		});

        
        ListDataProvider<FeatureTO> dataProvider = new ListDataProvider<>(features);
        add(new DataTable<FeatureTO, String>("services", columns, dataProvider, 100));
        add(new Label("instance", servicePointer.getProperties().toString()));
	}
}
