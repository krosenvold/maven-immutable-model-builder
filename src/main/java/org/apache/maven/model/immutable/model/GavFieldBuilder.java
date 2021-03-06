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
                gavState.groupId = groupIdBuilder.build( node );
                return true;
            case "artifactId":
                gavState.artifactId = artifactIdBuilder.build( node );
                return true;
            case "version":
                gavState.version = versionBuilder.build( node );
                return true;
            default:
                return false;
        }
    }
}
