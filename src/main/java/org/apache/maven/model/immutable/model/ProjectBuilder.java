package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.Properties;

class ProjectBuilder
    implements ItemBuilder<ImmProject>
{
    private final BuilderBuild build = new BuilderBuild();

    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final GenericListBuilder<ImmMailingList> mailingListsBuilder =
        new GenericListBuilder<>( "mailingList", new MailingListBuilder() );

    private final ParentBuilder parentBuilder = new ParentBuilder();

    private final ScmBuilder scmBuilder = new ScmBuilder();

    private final IssueManagementBuilder issueManagementBuilder = new IssueManagementBuilder();

    private final CiManagementBuilder ciManagementBuilder = new CiManagementBuilder();

    private final GenericListBuilder<ImmContributor> contributorsBuilder =
        new GenericListBuilder<>( "contributor", new ContributorBuilder() );

    private final PrerequisitesBuilder prerequisitesBuilder = new PrerequisitesBuilder();

    private final ModelBaseFieldBuilder modelBaseFieldBuilder = new ModelBaseFieldBuilder();

    private final GavFieldBuilder gavFieldBuilder = new GavFieldBuilder();

    private final ReportingBuilder reportingBuilder = new ReportingBuilder();

    private final ProfileBuilder profileBuilder = new ProfileBuilder( build, reportingBuilder );

    private final GenericListBuilder<ImmProfile> profilesBuilder =
        new GenericListBuilder<>( "profile", profileBuilder );


    public final ImmProject build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        ImmBuild build = null;
        ImmModelVersion2 modelVersion = null;
        ImmProjectName name = null;
        ImmProjectDescription description = null;
        ImmProjectUrl url = null;
        List<ImmMailingList> mailingLists = null;
        ImmParent parent = null;
        String packaging = null;
        String inceptionYear = null;
        ImmScm scm = null;
        ImmIssueManagement issueManagement = null;
        ImmCiManagement ciManagement = null;
        List<ImmContributor> contributors = null;
        Properties prerequisites = null;
        List<ImmProfile> profiles = null;

        ModelBaseState mbState = new ModelBaseState();
        GavState gavState = new GavState();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( !modelBaseFieldBuilder.build( node, mbState ) && !gavFieldBuilder.build( node, gavState ) )
                    {
                        switch ( node.getLocalName() )
                        {
                            case "build":
                                build = this.build.build( node );
                                break;
                            case "modelVersion":
                                modelVersion = new ImmModelVersion2( leafBuilder.build( node ) );
                                break;
                            case "name":
                                name = new ImmProjectName( leafBuilder.build( node ) );
                                break;
                            case "description":
                                description = new ImmProjectDescription( leafBuilder.build( node ) );
                                break;
                            case "url":
                                url = new ImmProjectUrl( leafBuilder.build( node ) );
                                break;
                            case "mailingLists":
                                mailingLists = mailingListsBuilder.build( node );
                                break;
                            case "parent":
                                parent = parentBuilder.build( node );
                                break;
                            case "packaging":
                                packaging = leafBuilder.build( node );
                                break;
                            case "inceptionYear":
                                inceptionYear = leafBuilder.build( node );
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
                            case "contributors":
                                contributors = contributorsBuilder.build( node );
                                break;
                            case "prerequisites":
                                prerequisites = prerequisitesBuilder.build( node );
                                break;
                            case "profiles":
                                profiles = profilesBuilder.build( node );
                                break;
                            default:
                                throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                        }
                    }
            }
        }
        return new ImmProject( mbState, gavState.gav(), build, modelVersion, prerequisites,
                               contributors, ciManagement, issueManagement, scm, inceptionYear, packaging, mailingLists,
                               parent, name, description, url, profiles );

    }
}
