package org.apache.maven.model.immutable.model;

public class ImmDependency
{
    private final ImmGroupId groupId;

    private final ImmArtifactId artifactId;

    private final ImmVersion version;

    private final String classifier;

    private final String type;

    private final String systemPath;

    private final String optional;

    private final String scope;

    public ImmDependency( ImmGroupId groupId, ImmArtifactId artifactId, ImmVersion version, String classifier,
                          String type, String systemPath, String optional, String scope )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.classifier = classifier;
        this.type = type;
        this.systemPath = systemPath;
        this.optional = optional;
        this.scope = scope;
    }
}
