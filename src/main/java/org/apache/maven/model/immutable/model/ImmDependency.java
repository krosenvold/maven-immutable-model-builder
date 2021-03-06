package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Dependency;

import java.util.function.Function;

public class ImmDependency
{
    private final String groupId;

    private final String artifactId;

    private final String version;

    private final String classifier;

    private final String type;

    private final String systemPath;

    private final String optional;

    private final String scope;

    private final ImmList<ImmExclusion> exclusions;

    static final Function<ImmDependency, Dependency> mapper = new Function<ImmDependency, Dependency>()
    {
        @Override
        public Dependency apply( ImmDependency contributor )
        {
            return contributor.toDependency();
        }
    };

    public ImmDependency( String groupId, String artifactId, String version, String classifier, String type,
                          String systemPath, String optional, String scope, ImmList<ImmExclusion> exclusions )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.classifier = classifier;
        this.type = type;
        this.systemPath = systemPath;
        this.optional = optional;
        this.scope = scope;
        this.exclusions = exclusions;
    }

    public Dependency toDependency()
    {
        Dependency result = new Dependency();
        result.setGroupId( groupId );
        result.setArtifactId( artifactId );
        result.setVersion( version );
        result.setType( type );
        result.setClassifier( classifier );
        result.setScope( scope );
        result.setSystemPath( systemPath );
        result.setOptional( Boolean.valueOf( optional ) );
        if ( exclusions != null )
        {
            result.setExclusions( exclusions.toList( ImmExclusion.mapper ) );
        }
        return result;
    }
}
