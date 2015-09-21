package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class BuildBuilder
{
    private final PluginsBuilder pluginsBuilder = new PluginsBuilder();

    public Build build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        Plugins plugins = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "plugins".equals( localName ) )
                    {
                        plugins = pluginsBuilder.build( node );
                    }
                    else
                    {
                        throw new RuntimeException( "Unsupported child tag" + localName );
                    }
            }
        }
        return new Build( plugins );

    }
}
