package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class ReportingBuilder
{

    PluginsBuilder pluginsBuilder = new PluginsBuilder();

    public Reporting build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        Plugins build = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "plugins".equals( node.getLocalName() ) )
                    {
                        build = pluginsBuilder.build( node );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }

            }
        }
        return new Reporting( build );
    }

}
