package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

public class ImmPlugin2
    extends ModelElement
{
    private final ImmGroupId groupId;

    private final ImmArtifactId artifactId;

    private final ImmVersion version;

    private final String configuration;

    private final String reportSets;


    public ImmPlugin2( ImmArtifactId artifactId, ImmGroupId groupId, ImmVersion version, String configuration,
                       String reportSets )
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