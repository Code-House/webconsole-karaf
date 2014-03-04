package org.webconsole.karaf.features.dto;

public class FeatureTO {

    private final String name;
    private final String version;

    private final boolean installed;

    public FeatureTO(String name, String version, boolean installed) {
        this.name = name;
        this.version = version;
        this.installed = installed;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public boolean isInstalled() {
        return installed;
    }
}
