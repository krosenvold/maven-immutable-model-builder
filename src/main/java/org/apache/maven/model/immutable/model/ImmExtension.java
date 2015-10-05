package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Extension;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ImmExtension
{
    private final String groupId;

    private final String artifactId;

    private final String version;

    static final Function<ImmExtension, Extension> mapper = new Function<ImmExtension, Extension>()
    {
        @Override
        public Extension apply( ImmExtension immExtension )
        {
            return immExtension.toExtenstion();
        }
    };

    public ImmExtension( String groupId, String artifactId, String version )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public Extension toExtenstion()
    {
        Extension result = new Extension();
        result.setGroupId( groupId );
        result.setArtifactId( artifactId );
        result.setVersion( version );
        return result;
    }

    static List<Extension> asList( List<ImmExtension> src )
    {
        List<Extension> result = new ArrayList<>();
        for ( ImmExtension immDependency : src )
        {
            result.add( immDependency.toExtenstion() );
        }
        return result;
    }
}
