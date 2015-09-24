package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class GavBuilder
{
    GroupId groupId = null;

    ArtifactId artifactId = null;

    Version version = null;

    private final LeafBuilder groupIdBuilder = new LeafBuilder();

    private final LeafBuilder artifactIdBuilder = new LeafBuilder();

    private final LeafBuilder versionBuilder = new LeafBuilder();


    public boolean build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        String localName = node.getLocalName();
        switch ( localName )
        {
            case "groupId":
                groupId = new GroupId( groupIdBuilder.singleTextValue( node ) );
                return true;
            case "artifactId":
                artifactId = new ArtifactId( artifactIdBuilder.singleTextValue( node ) );
                return true;
            case "version":
                version = new Version( versionBuilder.singleTextValue( node ) );
                return true;
            default:
                return false;
        }
    }

    public Gav gav(){
        return new Gav( groupId, artifactId, version );
    }


}
