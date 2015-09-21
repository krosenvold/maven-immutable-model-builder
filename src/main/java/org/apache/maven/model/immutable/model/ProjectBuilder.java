package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.stax2.XMLStreamReader2;

/**
 * Created by kristian on 20.09.15.
 */
public class ProjectBuilder
    implements Builder<Project>
{
    private final BuildBuilder build = new BuildBuilder();

    @Override
    public Builder getBuilderFor( String tagName )
    {
        if ( "build".equals( tagName ) )
        {
            return build;
        }
        throw new IllegalArgumentException( "Unsupported tag " + tagName );
    }

    @Override
    public Project from( XMLStreamReader2 node, Iterable<ModelElement> kids, String nodeText )
    {
        return new Project( kids );
    }
}
