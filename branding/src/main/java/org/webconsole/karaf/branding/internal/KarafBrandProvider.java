package org.webconsole.karaf.branding.internal;

import java.util.Collections;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.webconsole.api.BrandProvider;
import org.webconsole.ui.bootstrap.BootstrapResources;

public class KarafBrandProvider implements BrandProvider {

    @Override
    public Component getHeader(String headerId) {
        return new EmptyPanel(headerId);
    }

    @Override
    public List<Behavior> getBehaviors() {
        return Collections.emptyList();
    }

    @Override
    public void modify(Page page) {
        page.add(new BootstrapResources());
    }

    @Override
    public Component getFooter(String footerId) {
        return new Label(footerId, "Apache &reg; Karaf WebConsole");
    }

}
