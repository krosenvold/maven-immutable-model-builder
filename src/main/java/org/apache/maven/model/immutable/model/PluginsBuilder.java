package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.stax2.XMLStreamReader2;

/**
 * Created by kristian on 20.09.15.
 */
public class PluginsBuilder
    implements Builder<Plugins>
{
    private final PluginBuilder pluginBuilder = new PluginBuilder();

    @Override
    public Builder getBuilderFor( String tagName )
    {
        if ( "plugin".equals( tagName ) )
        {
            return pluginBuilder;
        }
        throw new IllegalArgumentException( "Unsupported tag " + tagName );
    }

    @Override
    public Plugins from( XMLStreamReader2 node, Iterable<ModelElement> kids, String nodeText )
    {
        return new Plugins( kids );
    }
}
