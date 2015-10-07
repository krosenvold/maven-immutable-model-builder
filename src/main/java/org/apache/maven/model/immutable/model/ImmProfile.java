package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Profile;

import java.util.List;

public class ImmProfile
    extends ImmModelBase
{
    private final String id;

    private final ImmActivation activation;

    private final ImmBuild build;

    public ImmProfile( ModelBaseState mbState, String id, ImmActivation activation, ImmBuild build,
                       ImmReporting reporting, ImmList<ImmDependency> dependencies,
                       ImmList<ImmDependency> dependencyManagement )
    {

        super( mbState );
        this.id = id;
        this.activation = activation;
        this.build = build;
    }

    public Profile toProfile()
    {
        Profile profile = new Profile();
        setModelBase( profile );
        profile.setId( id );
        if ( activation != null )
        {
            profile.setActivation( activation.toActivation() );
        }
        if ( build != null )
        {
            profile.setBuild( build.toBuild() );
        }
        return profile;

    }
}
