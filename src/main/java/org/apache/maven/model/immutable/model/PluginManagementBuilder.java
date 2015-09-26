package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

class PluginManagementBuilder
{

    private final GenericListBuilder<ImmPlugin> pluginBuilder;

    public PluginManagementBuilder( GenericListBuilder<ImmPlugin> pluginsBuilder )
    {
        this.pluginBuilder = pluginsBuilder;
    }


    public List<ImmPlugin> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "plugins".equals( node.getLocalName() ) )
                    {
                        return pluginBuilder.build( node );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }

            }
        }
        return null;
    }
}
