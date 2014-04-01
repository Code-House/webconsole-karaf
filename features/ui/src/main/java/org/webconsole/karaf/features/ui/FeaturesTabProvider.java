package org.webconsole.karaf.features.ui;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.webconsole.api.ConsoleTabProvider;

public class FeaturesTabProvider implements ConsoleTabProvider {

    @Override
    public List<Link<Page>> getItems(String componentId, String labelId) {
        return Collections.emptyList();
    }

    @Override
    public Link<Page> getModuleLink(String componentId, String labelId) {
        BookmarkablePageLink<Page> link = new BookmarkablePageLink<>(componentId, FeaturesPage.class);
        link.add(new Label(labelId, "Features"));
        return link;
    }

}
