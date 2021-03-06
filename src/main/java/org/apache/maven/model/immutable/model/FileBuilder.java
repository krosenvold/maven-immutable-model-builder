package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class FileBuilder
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public ImmFile build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String missing = null;
        String exists = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {
                        case "missing":
                            missing = leafBuilder.build( node );
                            break;
                        case "exists":
                            exists = leafBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );

                    }
            }
        }

        return new ImmFile( exists, missing );
    }
}
