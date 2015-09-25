package org.apache.maven.model.immutable.model;

class Gav
{

    final ImmGroupId groupId;

    final ImmArtifactId artifactId;

    final ImmVersion version;

    Gav( ImmGroupId groupId, ImmArtifactId artifactId, ImmVersion version )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }
}
