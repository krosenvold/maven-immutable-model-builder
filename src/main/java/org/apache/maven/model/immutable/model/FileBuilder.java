package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class FileBuilder
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public File build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String missing = null;
        String exists = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "missing":
                            missing = leafBuilder.singleTextValue( node );
                            break;
                        case "exists":
                            exists = leafBuilder.singleTextValue( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );

                    }
            }
        }

        return new File( exists, missing);
    }
}
