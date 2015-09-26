package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class OsBuilder
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public ImmOs build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String arch = null;
        String family = null;
        String name = null;
        String version = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "arch":
                            arch = leafBuilder.build( node );
                            break;
                        case "family":
                            family = leafBuilder.build( node );
                            break;
                        case "name":
                            name = leafBuilder.build( node );
                            break;
                        case "version":
                            version = leafBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );

                    }
            }
        }

        return new ImmOs( family, name, arch, version );
    }
}
