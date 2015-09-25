package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class GavBuilder
{
    private ImmGroupId groupId = null;

    private ImmArtifactId artifactId = null;

    private ImmVersion version = null;

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
                groupId = new ImmGroupId( groupIdBuilder.singleTextValue( node ) );
                return true;
            case "artifactId":
                artifactId = new ImmArtifactId( artifactIdBuilder.singleTextValue( node ) );
                return true;
            case "version":
                version = new ImmVersion( versionBuilder.singleTextValue( node ) );
                return true;
            default:
                return false;
        }
    }

    public Gav gav()
    {
        return new Gav( groupId, artifactId, version );
    }


}
