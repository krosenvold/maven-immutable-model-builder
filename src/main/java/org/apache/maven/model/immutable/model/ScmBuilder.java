package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ScmBuilder
    implements ItemBuilder<ImmScm>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public final ImmScm build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        String connection = null;
        String developerConnection = null;
        String url = null;
        String tag = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {
                        case "connection":
                            connection = this.leafBuilder.build( node );
                            break;
                        case "developerConnection":
                            developerConnection = leafBuilder.build( node );
                            break;
                        case "url":
                            url = leafBuilder.build( node );
                            break;
                        case "tag":
                            tag = leafBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                    }
            }
        }
        return new ImmScm( connection, developerConnection, url, tag );

    }
}
