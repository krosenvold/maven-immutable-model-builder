package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.stax2.XMLStreamReader2;

/**
 * Created by kristian on 20.09.15.
 */
public class RootBuilder
    implements Builder<Build>
{
    private final ProjectBuilder projectBuilder = new ProjectBuilder();

    public Builder getBuilderFor( String tagName )
    {
        if ( tagName.equals( "project" ) )
        {
            return projectBuilder;
        }
        throw new RuntimeException( "Unsupported child tag" + tagName );
    }

    @Override
    public Build from( XMLStreamReader2 node, Iterable<ModelElement> kids, String nodeText )
    {
        return null;
    }
}
