package org.webconsole.karaf.features.dto;

import org.webconsole.karaf.features.dto.Feature;
import org.webconsole.karaf.features.dto.Repository;

import java.util.List;

public interface FeaturesServiceAdapter {

    List<Feature> getFeatures();

    List<Repository> getRepositories();

    void removeRepository(Repository repository);

    void installFeature(Feature feature);

    void uninstallFeature(Feature feature);

}
