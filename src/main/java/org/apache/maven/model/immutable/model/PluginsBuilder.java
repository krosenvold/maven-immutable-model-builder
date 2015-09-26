package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

class PluginsBuilder
{
    private final PluginBuilder pluginBuilder = new PluginBuilder();


    public ImmPlugins build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<ImmPlugin> plugins = new ArrayList<>();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "plugin".equals( node.getLocalName() ) )
                    {
                        plugins.add( pluginBuilder.build( node ) );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }

            }
        }
        return new ImmPlugins( plugins );
    }
}
