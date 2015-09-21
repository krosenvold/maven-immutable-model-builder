package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.stax2.XMLStreamReader2;

/**
 * Created by kristian on 20.09.15.
 */
public class BuildBuilder
    implements Builder<Build>
{
    private final PluginsBuilder pluginsBuilder = new PluginsBuilder();

    public Builder getBuilderFor( String tagName )
    {
        if ( tagName.equals( "plugins" ) )
        {
            return pluginsBuilder;
        }
        throw new RuntimeException( "Unsupported child tag" + tagName );
    }

    @Override
    public Build from( XMLStreamReader2 node, Iterable<ModelElement> kids, String nodeText )
    {
        ModelElement next = kids.iterator().next();
        return new Build( (Plugins) next );
    }
}
