package org.webconsole.karaf.features.dto;

import org.webconsole.karaf.features.dto.FeatureTO;
import org.webconsole.karaf.features.dto.RepositoryTO;

import java.util.List;

public interface FeaturesServiceAdapter {

    List<FeatureTO> getFeatures();

    List<RepositoryTO> getRepositories();

    void removeRepository(RepositoryTO repository);

    void installFeature(FeatureTO feature);

    void uninstallFeature(FeatureTO feature);

}
