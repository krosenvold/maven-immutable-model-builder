package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class GavFieldBuilder
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();

    private final LeafBuilder artifactIdBuilder = new LeafBuilder();

    private final LeafBuilder versionBuilder = new LeafBuilder();


    public boolean build( XMLStreamReader2 node, GavState gavState )
        throws XMLStreamException
    {
        String localName = node.getLocalName();
        switch ( localName )
        {
            case "groupId":
                gavState.groupId = new ImmGroupId( groupIdBuilder.singleTextValue( node ) );
                return true;
            case "artifactId":
                gavState.artifactId = new ImmArtifactId( artifactIdBuilder.singleTextValue( node ) );
                return true;
            case "version":
                gavState.version = new ImmVersion( versionBuilder.singleTextValue( node ) );
                return true;
            default:
                return false;
        }
    }
}
