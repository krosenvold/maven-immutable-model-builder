package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Plugin;
import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.plexus.util.StringInputStream;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;

public class ImmPlugin
    extends ModelElement
{
    private final ImmGroupId groupId;

    private final ImmArtifactId artifactId;

    private final ImmVersion version;

    private final String configuration;

    private final String reportSets;


    public ImmPlugin( ImmArtifactId artifactId, ImmGroupId groupId, ImmVersion version, String configuration,
                      String reportSets )
    {
        this.artifactId = artifactId;
        this.groupId = groupId;
        this.version = version;
        this.configuration = configuration;
        this.reportSets = reportSets;
    }

    @Override
    public String toString()
    {
        return groupId.getGroupId() + ":" + artifactId.getArtifactId() + ":" + version.getVersion();
    }

    public Plugin toPlugin()
    {
        Plugin plugin = new Plugin();
        if ( groupId != null )
        {
            plugin.setGroupId( groupId.getGroupId() );
        }
        if ( artifactId != null )
        {
            plugin.setArtifactId( artifactId.getArtifactId() );
        }
        if ( version != null )
        {
            plugin.setVersion( version.getVersion() );
        }
        // plugin.setExtensions(  ); TODO
        // plugin.reportSets TODO
        try
        {
            plugin.setConfiguration( Xpp3DomBuilder.build( new StringInputStream( configuration ), "UTF-8" ) );
        }
        catch ( XmlPullParserException e )
        {
            throw new RuntimeException( e );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        }
        return plugin;
    }
}
