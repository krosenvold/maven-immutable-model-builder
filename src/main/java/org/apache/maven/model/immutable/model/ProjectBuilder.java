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

    private final LeafBuilder modelVersionBuilder = new LeafBuilder();

    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final MailingListsBuilder mailingListsBuilder = new MailingListsBuilder();
    public Project build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        Build build = null;
        ModelVersion modelVersion = null;
        GroupId groupId = null;
        ArtifactId artifactId = null;
        Version version = null;
        ProjectName name = null;
        ProjectDescription description = null;
        ProjectUrl url = null;
        MailingLists mailingLists = null;

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
                            modelVersion = new ModelVersion( modelVersionBuilder.singleTextValue( node ) );
                            break;
                        case "groupId":
                            groupId = new GroupId( groupIdBuilder.singleTextValue( node ) );
                            break;
                        case "artifactId":
                            artifactId = new ArtifactId( artifactIdBuilder.singleTextValue( node ) );
                            break;
                        case "version":
                            version = new Version( versionBuilder.singleTextValue( node ) );
                            break;
                        case "name":
                            name = new ProjectName( leafBuilder.singleTextValue( node ) );
                            break;
                        case "description":
                            description = new ProjectDescription( leafBuilder.singleTextValue( node ) );
                            break;
                        case "url":
                            url = new ProjectUrl( leafBuilder.singleTextValue( node ) );
                            break;
                        case "mailingLists":
                            mailingLists = mailingListsBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag" + localName );
                    }
            }
        }
        return new Project( build, modelVersion, groupId, artifactId, version );

    }
}
