package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class ImmDependency
{
    private final ImmGroupId groupId;

    private final ImmArtifactId artifactId;

    private final ImmVersion version;

    private final String classifier;

    private final String type;

    private final String systemPath;

    private final String optional;

    private final String scope;

    public ImmDependency( ImmGroupId groupId, ImmArtifactId artifactId, ImmVersion version, String classifier,
                          String type, String systemPath, String optional, String scope )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.classifier = classifier;
        this.type = type;
        this.systemPath = systemPath;
        this.optional = optional;
        this.scope = scope;
    }

    public Dependency toDependency()
    {
        Dependency result = new Dependency();
        result.setGroupId( groupId.getGroupId() );
        result.setArtifactId( artifactId.getArtifactId() );
        result.setVersion( version.getVersion() );
        result.setType( type );
        result.setClassifier( classifier );
        result.setScope( scope );
        result.setSystemPath( systemPath );
        result.setOptional( Boolean.valueOf( optional ) );
        return result;
    }

    static List<Dependency> asList( List<ImmDependency> src )
    {
        List<Dependency> result = new ArrayList<>();
        for ( ImmDependency immDependency : src )
        {
            result.add( immDependency.toDependency() );
        }
        return result;
    }
}
