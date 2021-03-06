package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class BuilderActivation
{
    private final BuilderActivationProperties propertiesBuilder = new BuilderActivationProperties();

    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final FileBuilder fileBuilder = new FileBuilder();

    private final OsBuilder osBuilder = new OsBuilder();

    public ImmActivation build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        ImmActivationProperty properties = null;
        String jdk = null;
        ImmFile file = null;
        ImmOs os = null;
        String activeByDefault = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "property":
                            properties = propertiesBuilder.build( node );
                            break;
                        case "jdk":
                            jdk = leafBuilder.build( node );
                            break;
                        case "activeByDefault":
                            activeByDefault = leafBuilder.build( node );
                            break;
                        case "os":
                            os = osBuilder.build( node );
                            break;
                        case "file":
                            file = fileBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );

                    }
            }
        }
        return new ImmActivation( properties, jdk, file, activeByDefault, os );
    }
}
