package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ParentBuilder
{
    private final LeafBuilder rp = new LeafBuilder();

    public Parent build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String relativePath = null;

        GavBuilder gavBuilder = new GavBuilder();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if (!gavBuilder.build(  node ))
                    {
                        String localName = node.getLocalName();

                        switch ( localName )
                        {
                            case "relativePath":
                                relativePath = rp.singleTextValue( node );
                                break;
                            default:
                                throw new IllegalArgumentException( "Unsupported tag " + localName );
                        }
                    }
            }

        }
        return new Parent( relativePath, gavBuilder.gav() );
    }
}
