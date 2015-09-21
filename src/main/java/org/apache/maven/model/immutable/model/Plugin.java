package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class Plugin
    extends ModelElement
{
    private final ArtifactId artifactId;

    private final GroupId groupId;

    private final Version version;

    public Plugin( ArtifactId artifactId, GroupId groupId, Version version )
    {
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.version = version;
    }

    public Plugin( Iterable<ModelElement> modelElements )
    {
        this( selectArtifact( modelElements ), selectGroupId( modelElements ), selectVersion( modelElements ) );
    }

    static ArtifactId selectArtifact( Iterable<ModelElement> modelElements )
    {
        for ( ModelElement modelElement : modelElements )
        {
            if ( modelElement instanceof ArtifactId )
            {
                return (ArtifactId) modelElement;
            }
        }
        return null;
    }

    static GroupId selectGroupId( Iterable<ModelElement> modelElements )
    {
        for ( ModelElement modelElement : modelElements )
        {
            if ( modelElement instanceof GroupId )
            {
                return (GroupId) modelElement;
            }
        }
        return null;
    }

    static Version selectVersion( Iterable<ModelElement> modelElements )
    {
        for ( ModelElement modelElement : modelElements )
        {
            if ( modelElement instanceof Version )
            {
                return (Version) modelElement;
            }
        }
        return null;
    }
}
