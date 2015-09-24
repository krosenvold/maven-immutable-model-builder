package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class PluginBuilder
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();

    private final LeafBuilder artifactIdBuilder = new LeafBuilder();

    private final LeafBuilder versionBuilder = new LeafBuilder();


    public Plugin build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        GroupId groupId = null;
        ArtifactId artifactId = null;
        Version version = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "groupId":
                            groupId = new GroupId( groupIdBuilder.singleTextValue( node ) );
                            break;
                        case "artifactId":
                            artifactId = new ArtifactId( artifactIdBuilder.singleTextValue( node ) );
                            break;
                        case "version":
                            version = new Version( versionBuilder.singleTextValue( node ) );
                            break;
                    }
            }
        }

        return new Plugin( artifactId, groupId, version );
    }
}
