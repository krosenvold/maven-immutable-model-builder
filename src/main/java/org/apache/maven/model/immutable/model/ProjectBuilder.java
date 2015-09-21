package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ProjectBuilder
    extends BaseBuilder
{
    private final BuildBuilder build = new BuildBuilder();

    private final GroupIdBuilder groupIdBuilder = new GroupIdBuilder();

    private final ArtifactIdBuilder artifactIdBuilder = new ArtifactIdBuilder();

    private final VersionBuilder versionBuilder = new VersionBuilder();

    private final ModelVersionBuilder modelVersionBuilder = new ModelVersionBuilder();

    public Project build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        Build build = null;
        ModelVersion modelVersion = null;
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
                        case "build":
                            build = this.build.build( node );
                            break;
                        case "modelVersion":
                            modelVersion = modelVersionBuilder.build( node );
                            break;
                        case "groupId":
                            groupId = groupIdBuilder.build( node );
                            break;
                        case "artifactId":
                            artifactId = artifactIdBuilder.build( node );
                            break;
                        case "version":
                            version = versionBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag" + localName );
                    }
            }
        }
        return new Project( build, modelVersion, groupId, artifactId, version );

    }
}
