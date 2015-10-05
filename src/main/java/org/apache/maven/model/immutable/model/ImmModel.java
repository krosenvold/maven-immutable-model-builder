package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Model;

import java.util.List;
import java.util.Properties;

public class ImmModel
    extends ImmModelBase
{

    private final ModelBaseState modelBaseState;

    private final Gav gavState;

    private final ImmBuild build;

    private final String modelVersion;

    private final Properties prerequisites;

    private final ImmList<ImmContributor> contributors;

    private final ImmCiManagement ciManagement;

    private final ImmIssueManagement issueManagement;

    private final ImmScm scm;

    private final String year;

    private final String packaging;

    private final ImmList<ImmMailingList> mailingLists;

    private final ImmParent parent;

    private final ImmProjectName name;

    private final ImmProjectDescription description;

    private final ImmProjectUrl url;

    private final ImmList<ImmProfile> profiles;

    private final ImmList<ImmDeveloper> developers;

    public ImmModel( ModelBaseState modelBaseState, Gav gavState, ImmBuild build, String modelVersion,
                     Properties prerequisites, ImmList<ImmContributor> contributors, ImmCiManagement ciManagement,
                     ImmIssueManagement issueManagement, ImmScm scm, String year, String packaging,
                     ImmList<ImmMailingList> mailingLists, ImmParent parent, ImmProjectName name,
                     ImmProjectDescription description, ImmProjectUrl url, ImmList<ImmProfile> profiles,
                     ImmList<ImmDeveloper> developers )
    {
        super( modelBaseState );
        this.modelBaseState = modelBaseState;
        this.gavState = gavState;
        this.build = build;
        this.modelVersion = modelVersion;
        this.prerequisites = prerequisites;
        this.contributors = contributors;
        this.ciManagement = ciManagement;
        this.issueManagement = issueManagement;
        this.scm = scm;
        this.year = year;
        this.packaging = packaging;
        this.mailingLists = mailingLists;
        this.parent = parent;
        this.name = name;
        this.description = description;
        this.url = url;
        this.profiles = profiles;
        this.developers = developers;
    }

    public ImmBuild getBuild()
    {
        return build;
    }

    public String getModelVersion()
    {
        return modelVersion;
    }

    public String getGroupId()
    {
        return gavState.groupId;
    }

    public String getArtifactId()
    {
        return gavState.artifactId;
    }

    public String getVersion()
    {
        return gavState.version;
    }

    public Model toModel(){
        Model result = new Model();
        result.setModelVersion( modelVersion );
        gavState.setModelAttrs( result);
        modelBaseState.setModelBaseAttributes( result );
        result.setDevelopers( developers.toList( ImmDeveloper.mapper ) );
        result.setContributors( contributors.toList( ImmContributor.mapper));
        result.setCiManagement( ciManagement.toCiManagement() );
        result.setUrl( url.getUrl() );
        result.setBuild( build.toBuild() );
        // todo: a bunch of attrs
        return result;

    }

}
