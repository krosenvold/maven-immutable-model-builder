package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.Properties;

class ProjectBuilder
    extends BaseBuilder
{
    private final BuildBuilder build = new BuildBuilder();

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

    private final DependenciesBuilder dependenciesBuilder = new DependenciesBuilder();

    private final DependencyManagementBuilder dependencyManagementBuilder =
        new DependencyManagementBuilder( dependenciesBuilder );

    private final ProfilesBuilder profilesBuilder = new ProfilesBuilder( build, reportingBuilder );

    public ImmProject build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        ImmBuild build = null;
        ImmModelVersion2 modelVersion = null;
        ImmGroupId groupId = null;
        ImmArtifactId artifactId = null;
        ImmVersion version = null;
        ImmProjectName name = null;
        ImmProjectDescription description = null;
        ImmProjectUrl url = null;
        ImmMailingLists mailingLists = null;
        ImmReporting reporting = null;
        ImmParent parent = null;
        String packaging = null;
        String inceptionYear = null;
        Properties properties = null;
        List<String> modules = null;
        ImmScm scm;
        ImmIssueManagement issueManagement;
        CiManagement ciManagement;
        DistributionManagement distributionManagement;
        List<Contributor> contributors;
        Properties prerequisites;
        List<ImmDependency> dependencyManagement;
        List<ImmDependency> dependencies;
        List<ImmProfile> profiles;


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
                            modelVersion = new ImmModelVersion2( leafBuilder.singleTextValue( node ) );
                            break;
                        case "groupId":
                            groupId = new ImmGroupId( leafBuilder.singleTextValue( node ) );
                            break;
                        case "artifactId":
                            artifactId = new ImmArtifactId( leafBuilder.singleTextValue( node ) );
                            break;
                        case "version":
                            version = new ImmVersion( leafBuilder.singleTextValue( node ) );
                            break;
                        case "name":
                            name = new ImmProjectName( leafBuilder.singleTextValue( node ) );
                            break;
                        case "description":
                            description = new ImmProjectDescription( leafBuilder.singleTextValue( node ) );
                            break;
                        case "url":
                            url = new ImmProjectUrl( leafBuilder.singleTextValue( node ) );
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
        return new ImmProject( build, modelVersion, groupId, artifactId, version );

    }
}
