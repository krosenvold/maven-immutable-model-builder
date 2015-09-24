package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class SiteBuilder
    extends BaseBuilder
{
    private final LeafBuilder leafBuilder = LeafBuilder.INSTANCE;

    public Site build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        String connection = null;
        String url = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {

                        case "id":
                            connection = this.leafBuilder.singleTextValue( node );
                            break;
                        case "url":
                            url = leafBuilder.singleTextValue( node ) ;
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );
                    }
            }
        }
        return new Site( connection, url );

    }
}
