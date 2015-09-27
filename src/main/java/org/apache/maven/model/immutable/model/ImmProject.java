package org.apache.maven.model.immutable.model;

import java.util.List;
import java.util.Properties;

public class ImmProject
    extends ImmModelBase
{

    private final ModelBaseState modelBaseState;

    private final Gav gavState;

    private final ImmBuild build;

    private final ImmModelVersion2 modelVersion;

    private final Properties prerequisites;

    private final List<ImmContributor> contributors;

    private final ImmCiManagement ciManagement;

    private final ImmIssueManagement issueManagement;

    private final ImmScm scm;

    private final String year;

    private final String packaging;

    private final List<ImmMailingList> mailingLists;

    private final ImmParent parent;

    private final ImmProjectName name;

    private final ImmProjectDescription description;

    private final ImmProjectUrl url;

    private final List<ImmProfile> profiles;

    public ImmProject( ModelBaseState modelBaseState, Gav gavState, ImmBuild build, ImmModelVersion2 modelVersion,
                       Properties prerequisites,
                       List<ImmContributor> contributors, ImmCiManagement ciManagement,
                       ImmIssueManagement issueManagement,
                       ImmScm scm, String year, String packaging, List<ImmMailingList> mailingLists, ImmParent parent,
                       ImmProjectName name, ImmProjectDescription description, ImmProjectUrl url,
                       List<ImmProfile> profiles )
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
    }

    public ImmBuild getBuild()
    {
        return build;
    }

    public ImmModelVersion2 getModelVersion()
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
}
