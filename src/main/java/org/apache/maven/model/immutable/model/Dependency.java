package org.apache.maven.model.immutable.model;

public class Dependency
{
    private final GroupId groupId;

    private final ArtifactId artifactId;

    private final Version version;

    private final String classifier;

    private final String type;

    private final String systemPath;

    private final String optional;

    private final String scope;

    public Dependency( GroupId groupId, ArtifactId artifactId, Version version, String classifier, String type,
                       String systemPath, String optional, String scope )
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
