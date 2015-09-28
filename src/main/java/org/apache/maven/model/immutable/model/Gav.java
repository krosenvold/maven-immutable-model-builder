package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Model;

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

    void setModelAttrs(Model model){
        model.setGroupId( groupId );
        model.setArtifactId(  artifactId );
        model.setVersion(  version );
    }

    public String toString()
    {
        return groupId + ":" + artifactId + ":" + version;
    }

}
