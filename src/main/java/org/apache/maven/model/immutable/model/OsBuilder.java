package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class OsBuilder
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public Os build( XMLStreamReader2 node )
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
                        //         <os><arch>i386</arch><family>debian</family><name>raspian</name><version>1.0</version></os>

                        case "arch":
                            arch = leafBuilder.singleTextValue( node );
                            break;
                        case "family":
                            family = leafBuilder.singleTextValue( node );
                            break;
                        case "name":
                            name = leafBuilder.singleTextValue( node );
                            break;
                        case "version":
                            version = leafBuilder.singleTextValue( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );

                    }
            }
        }

        return new Os( family, name, arch, version);
    }
}
