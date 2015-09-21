package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class Plugin
    extends ModelElement
{
    private final GroupId groupId;

    private final ArtifactId artifactId;

    private final Version version;

    public Plugin( ArtifactId artifactId, GroupId groupId, Version version )
    {
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.version = version;
    }

    @Override
    public String toString()
    {
        return groupId.getGroupId() + ":" + artifactId.getArtifactId() + ":" + version.getVersion();
    }
}
