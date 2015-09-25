package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

public class ImmProject
    extends ModelElement
{

    private final ImmBuild build;

    private final ImmModelVersion2 modelVersion;

    private final ImmGroupId groupId;

    private final ImmArtifactId artifactId;

    private final ImmVersion version;

    public ImmProject( ImmBuild build, ImmModelVersion2 modelVersion, ImmGroupId groupId, ImmArtifactId artifactId,
                       ImmVersion version )
    {
        this.build = build;
        this.modelVersion = modelVersion;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public ImmBuild getBuild()
    {
        return build;
    }

    public ImmModelVersion2 getModelVersion()
    {
        return modelVersion;
    }

    public ImmGroupId getGroupId()
    {
        return groupId;
    }

    public ImmArtifactId getArtifactId()
    {
        return artifactId;
    }

    public ImmVersion getVersion()
    {
        return version;
    }
}
