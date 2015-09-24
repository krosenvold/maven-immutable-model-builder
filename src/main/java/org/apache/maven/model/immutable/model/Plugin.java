package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

public class Plugin
    extends ModelElement
{
    private final GroupId groupId;

    private final ArtifactId artifactId;

    private final Version version;

    private final String configuration;

    private final String reportSets;


    public Plugin( ArtifactId artifactId, GroupId groupId, Version version, String configuration, String reportSets )
    {
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.version = version;
        this.configuration = configuration;
        this.reportSets = reportSets;
    }

    @Override
    public String toString()
    {
        return groupId.getGroupId() + ":" + artifactId.getArtifactId() + ":" + version.getVersion();
    }
}
