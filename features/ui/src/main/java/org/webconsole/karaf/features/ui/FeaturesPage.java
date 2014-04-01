package org.webconsole.karaf.features.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.code_house.service.api.ServiceLocator;
import org.code_house.service.api.ServicePointer;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;
import org.webconsole.ui.pages.SecuredPage;

public class FeaturesPage extends SecuredPage {

    private static final long serialVersionUID = 1L;

    @Inject @Named("serviceLocator")
    private ServiceLocator serviceLocator;

    public FeaturesPage() {
        Set<ServicePointer<FeaturesServiceAdapter>> pointers = serviceLocator.lookup(FeaturesServiceAdapter.class);

        List<IColumn<ServicePointer<FeaturesServiceAdapter>, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<ServicePointer<FeaturesServiceAdapter>, String>(Model.of("id"), "properties"));
        ListDataProvider<ServicePointer<FeaturesServiceAdapter>> dataProvider = new ListDataProvider<>(new ArrayList<>(pointers));
        add(new DataTable<ServicePointer<FeaturesServiceAdapter>, String>("services", columns, dataProvider, 100));
    }

}
