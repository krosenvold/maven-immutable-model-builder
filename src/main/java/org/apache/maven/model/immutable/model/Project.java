package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

public class Project
    extends ModelElement
{

    private final Build build;

    private final ModelVersion modelVersion;

    private final GroupId groupId;

    private final ArtifactId artifactId;

    private final Version version;

    public Project( Build build, ModelVersion modelVersion, GroupId groupId, ArtifactId artifactId, Version version )
    {
        this.build = build;
        this.modelVersion = modelVersion;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public Build getBuild()
    {
        return build;
    }

    public ModelVersion getModelVersion()
    {
        return modelVersion;
    }

    public GroupId getGroupId()
    {
        return groupId;
    }

    public ArtifactId getArtifactId()
    {
        return artifactId;
    }

    public Version getVersion()
    {
        return version;
    }
}
