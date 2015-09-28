package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Dependency;

import java.util.ArrayList;
import java.util.List;

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

    private final List<ImmExclusion> exclusions;

    public ImmDependency( String groupId, String artifactId, String version, String classifier, String type, String systemPath, String optional, String scope,
                          List<ImmExclusion> exclusions )
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
        if (exclusions != null) result.setExclusions( ImmExclusion.toList( exclusions ));
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
