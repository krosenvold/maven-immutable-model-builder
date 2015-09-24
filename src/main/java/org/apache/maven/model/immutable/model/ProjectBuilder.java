package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.Properties;

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

    private final ReportingBuilder reportingBuilder = new ReportingBuilder();

    private final ParentBuilder parentBuilder = new ParentBuilder();

    private final PropertiesBuilder propertiesBuilder = new PropertiesBuilder();

    private final ModulesBuilder modulesBuilder = new ModulesBuilder();

    private final ScmBuilder scmBuilder = new ScmBuilder();

    private final IssueManagementBuilder issueManagementBuilder = new IssueManagementBuilder();

    private final CiManagementBuilder ciManagementBuilder = new CiManagementBuilder();

    private final DistributionManagementBuilder  distributionManagementBuilder = new DistributionManagementBuilder();

    private final ContributorsBuilder contributorsBuilder = new ContributorsBuilder();

    private final PrerequisitesBuilder prerequisitesBuilder = new PrerequisitesBuilder();

    private final DependencyManagementBuilder dependencyManagementBuilder = new DependencyManagementBuilder();

    private final DependenciesBuilder dependenciesBuilder = new DependenciesBuilder();

    private final ProfilesBuilder profilesBuilder = new ProfilesBuilder( build );

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
        Reporting reporting = null;
        Parent parent = null;
        String packaging = null;
        String inceptionYear = null;
        Properties properties = null;
        List<String> modules = null;
        Scm scm;
        IssueManagement issueManagement;
        CiManagement ciManagement;
        DistributionManagement distributionManagement;
        List<Contributor> contributors;
        Properties prerequisites;
        List<Dependency> dependencyManagement;
        List<Dependency> dependencies;
        List<Profile> profiles;


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
                        case "reporting":
                            reporting = reportingBuilder.build( node );
                            break;
                        case "parent":
                            parent = parentBuilder.build( node );
                            break;
                        case "packaging":
                            packaging =  leafBuilder.singleTextValue( node );
                            break;
                        case "inceptionYear":
                            inceptionYear =  leafBuilder.singleTextValue( node );
                            break;
                        case "properties":
                            properties =  propertiesBuilder.build( node );
                            break;
                        case "modules":
                            modules = modulesBuilder.build( node );
                            break;
                        case "scm":
                            scm = scmBuilder.build( node );
                            break;
                        case "issueManagement":
                            issueManagement = issueManagementBuilder.build( node );
                            break;
                        case "ciManagement":
                            ciManagement = ciManagementBuilder.build( node );
                            break;
                        case "distributionManagement":
                            distributionManagement = distributionManagementBuilder.build( node );
                            break;
                        case "contributors":
                            contributors = contributorsBuilder.build( node );
                            break;
                        case "prerequisites":
                            prerequisites = prerequisitesBuilder.build( node );
                            break;
                        case "dependencyManagement":
                            dependencyManagement = dependencyManagementBuilder.build( node );
                            break;
                        case "dependencies":
                            dependencies = dependenciesBuilder.build( node );
                            break;
                        case "profiles":
                            profiles = profilesBuilder.build( node );
                            break;





                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );
                    }
            }
        }
        return new Project( build, modelVersion, groupId, artifactId, version );

    }
}
