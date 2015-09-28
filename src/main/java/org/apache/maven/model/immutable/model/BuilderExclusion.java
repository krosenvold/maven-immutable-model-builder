package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class BuilderExclusion
    implements ItemBuilder<ImmExclusion>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    public final ImmExclusion build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String groupId = null;
        String artifactId = null;
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
                           default:
                              throw new RuntimeException( "Unsupported child tag" + node.getLocalName() );
                    }
            }
        }

        return new ImmExclusion( groupId, artifactId);
    }
}