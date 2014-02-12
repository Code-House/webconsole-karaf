package org.webconsole.karaf.features.jmx;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.apache.karaf.features.management.FeaturesServiceMBean;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;
import org.webconsole.karaf.features.dto.Feature;
import org.webconsole.karaf.features.dto.Repository;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import java.util.List;

public class JmxFeaturesService implements FeaturesServiceAdapter {

    private final FeaturesServiceMBean featuresService;

    public JmxFeaturesService(FeaturesServiceMBean featuresService) {
        this.featuresService = featuresService;
    }

    @Override
    public List<Feature> getFeatures() {
        try {
            TabularData features = featuresService.getFeatures();
            CompositeData compositeData = features.get(new Object[]{FeaturesServiceMBean.FEATURE});
            return Lists.newArrayList(Collections2.transform(compositeData.values(), new Function<Object, Feature>() {
                @Override
                public Feature apply(Object input) {
                    Object[] feature = (Object[]) input;
                    return new Feature((String) feature[0], (String) feature[1], false);
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Repository> getRepositories() {
        return null;
    }

    @Override
    public void removeRepository(Repository repository) {
        try {
            featuresService.removeRepository(repository.getUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void installFeature(Feature feature) {
        try {
            featuresService.installFeature(feature.getName(), feature.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uninstallFeature(Feature feature) {
        try {
            featuresService.uninstallFeature(feature.getName(), feature.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
