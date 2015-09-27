package org.apache.maven.model.immutable.model;

class Gav
{

    final String groupId;

    final String artifactId;

    final String version;

    Gav( String groupId, String artifactId, String version )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public String toString()
    {
        return groupId + ":" + artifactId + ":" + version;
    }

}
