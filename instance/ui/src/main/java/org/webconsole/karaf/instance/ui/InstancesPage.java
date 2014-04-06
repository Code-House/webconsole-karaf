package org.webconsole.karaf.instance.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.code_house.service.api.ServiceLocator;
import org.code_house.service.api.ServicePointer;
import org.webconsole.karaf.instance.dto.KarafInstanceServiceAdapter;
import org.webconsole.karaf.instance.dto.KarafInstanceTO;
import org.webconsole.ui.pages.SecuredPage;

public class InstancesPage extends SecuredPage
{

	private static final long serialVersionUID = 1L;

	@Inject
	@Named("serviceLocator")
	private ServiceLocator serviceLocator;

	public InstancesPage()
	{
		Set<ServicePointer<KarafInstanceServiceAdapter>> pointers = serviceLocator.lookup(KarafInstanceServiceAdapter.class);
		List<IColumn<ServicePointer<KarafInstanceServiceAdapter>, String>> columns = new ArrayList<>();

		columns.add(new PropertyColumn<ServicePointer<KarafInstanceServiceAdapter>, String>(Model.of("id"), "properties"));
		columns.add(new AbstractColumn<ServicePointer<KarafInstanceServiceAdapter>, String>(Model.of("links"))
		{
			@Override
			public void populateItem(Item<ICellPopulator<ServicePointer<KarafInstanceServiceAdapter>>> cellItem, String componentId,
					IModel<ServicePointer<KarafInstanceServiceAdapter>> rowModel)
			{

				Link<ServicePointer<KarafInstanceServiceAdapter>> link = new Link<ServicePointer<KarafInstanceServiceAdapter>>(componentId, rowModel)
				{
					@Override
					public void onClick()
					{
						System.out.println("onClick()");
					}
				};
				link.setBody(Model.of("Instance"));
				cellItem.add(link);

			}
		});

		ListDataProvider<ServicePointer<KarafInstanceServiceAdapter>> dataProvider = new ListDataProvider<>(new ArrayList<>(pointers));
		add(new DataTable<ServicePointer<KarafInstanceServiceAdapter>, String>("instances", columns, dataProvider, 100));
	}
}
