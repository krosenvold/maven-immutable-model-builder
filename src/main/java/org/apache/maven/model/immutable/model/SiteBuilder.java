package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class SiteBuilder
    implements ItemBuilder<ImmSite>
{
    private final LeafBuilder leafBuilder = LeafBuilder.INSTANCE;

    public final ImmSite build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        String id = null;
        String url = null;
        String name = null;


        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {
                        case "id":
                            id = this.leafBuilder.build( node );
                            break;
                        case "url":
                            url = leafBuilder.build( node );
                            break;
                        case "name":
                            name = leafBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                    }
            }
        }
        return new ImmSite( id, url, name );

    }
}
