package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

class ExtensionBuilder
    implements ItemBuilder<ImmExtension>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    public final ImmExtension build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String groupId = null;
        String artifactId = null;
        String version = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "groupId":
                            groupId = leafBuilder.build( node );
                            break;
                        case "artifactId":
                            artifactId = leafBuilder.build( node );
                            break;
                        case "version":
                            version = leafBuilder.build( node );
                            break;

                           default:
                              throw new RuntimeException( "Unsupported child tag" + node.getLocalName() );
                    }
            }
        }

        return new ImmExtension( groupId, artifactId, version);
    }
}