package org.webconsole.karaf.features.jmx;

import java.util.Collections;
import java.util.List;

import javax.management.openmbean.CompositeData;

import org.apache.karaf.features.management.FeaturesServiceMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webconsole.karaf.features.dto.FeatureTO;
import org.webconsole.karaf.features.dto.FeaturesServiceAdapter;
import org.webconsole.karaf.features.dto.RepositoryTO;
import org.webconsole.karaf.features.jmx.ts.TabularFunction;
import org.webconsole.karaf.features.jmx.ts.TabularStructures;

public class JmxFeaturesService implements FeaturesServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(JmxFeaturesService.class);
    private final FeaturesServiceMBean featuresService;

    public JmxFeaturesService(FeaturesServiceMBean featuresService) {
        this.featuresService = featuresService;
    }

    @Override
    public List<FeatureTO> getFeatures() {
        try {
            return TabularStructures.apply(featuresService.getFeatures(), new TabularFunction<String, FeatureTO>() {
                @Override
                public FeatureTO apply(List<String> key, CompositeData value) {
                    String name = (String) value.get(FeaturesServiceMBean.FEATURE_NAME);
                    String version = (String) value.get(FeaturesServiceMBean.FEATURE_VERSION);
                    Boolean installed = (Boolean) value.get(FeaturesServiceMBean.FEATURE_INSTALLED);
                    return new FeatureTO(name, version, installed);
                }
            });
        } catch (Exception e) {
            logger.error("Unable to fetch features", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<RepositoryTO> getRepositories() {
        try {
           return TabularStructures.apply(featuresService.getRepositories(), new TabularFunction<String, RepositoryTO>() {
                @Override
                public RepositoryTO apply(List<String> key, CompositeData value) {
                    return new RepositoryTO((String) value.get(FeaturesServiceMBean.REPOSITORY_NAME), (String) key.get(0));
                }
            });
        } catch (Exception e) {
            logger.error("Unable to fetch repositories", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void removeRepository(RepositoryTO repository) {
        try {
            featuresService.removeRepository(repository.getUri());
        } catch (Exception e) {
            logger.error("Unable to remove repository {}", repository.getUri(), e);
        }
    }

    @Override
    public void installFeature(FeatureTO feature) {
        try {
            featuresService.installFeature(feature.getName(), feature.getVersion());
        } catch (Exception e) {
            logger.error("Failed to install feature {}", feature.getName(), e);
        }
    }

    @Override
    public void uninstallFeature(FeatureTO feature) {
        try {
            featuresService.uninstallFeature(feature.getName(), feature.getVersion());
        } catch (Exception e) {
            logger.error("Failed to uninstall feature {}", feature.getName(), e);
        }
    }
}
