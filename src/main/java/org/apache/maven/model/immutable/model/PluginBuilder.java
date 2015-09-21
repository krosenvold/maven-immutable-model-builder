package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.stax2.XMLStreamReader2;

public class PluginBuilder
    implements Builder<Plugin>
{
    private final GroupIdBuilder groupIdBuilder = new GroupIdBuilder();

    private final ArtifactIdBuilder artifactIdBuilder = new ArtifactIdBuilder();

    private final VersionBuilder versionBuilder = new VersionBuilder();


    @Override
    public Builder getBuilderFor( String tagName )
    {
        if ( "groupId".equals( tagName ) )
        {
            return groupIdBuilder;
        }
        if ( "artifactId".equals( tagName ) )
        {
            return artifactIdBuilder;
        }
        if ( "version".equals( tagName ) )
        {
            return versionBuilder;
        }
        throw new RuntimeException( "Unsupported model" );
    }

    @Override
    public Plugin from( XMLStreamReader2 node, Iterable<ModelElement> kids, String nodeText )
    {
        return new Plugin( kids );
    }
}
