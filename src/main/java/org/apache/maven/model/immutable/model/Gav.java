package org.apache.maven.model.immutable.model;

class Gav
{

    final GroupId groupId;

    final ArtifactId artifactId;

    final Version version;

    Gav( GroupId groupId, ArtifactId artifactId, Version version )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }
}
