package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class PluginBuilder
{
    private final GroupIdBuilder groupIdBuilder = new GroupIdBuilder();

    private final ArtifactIdBuilder artifactIdBuilder = new ArtifactIdBuilder();

    private final VersionBuilder versionBuilder = new VersionBuilder();


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
                    if ( "groupId".equals( localName ) )
                    {
                        groupId = groupIdBuilder.build( node );
                    }
                    if ( "artifactId".equals( localName ) )
                    {
                        artifactId = artifactIdBuilder.build( node );
                    }
                    if ( "version".equals( localName ) )
                    {
                        version = versionBuilder.build( node );
                    }
            }
        }

        return new Plugin( artifactId, groupId, version );
    }
}
