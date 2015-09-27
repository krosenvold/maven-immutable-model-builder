package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.plexus.util.StringInputStream;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImmPlugin
    extends ModelElement
{
    private final Gav gav;

    private final String configuration;

    private final String reportSets;


    public ImmPlugin( Gav gav, String configuration,
                      String reportSets )
    {
        this.gav = gav;
        this.configuration = configuration;
        this.reportSets = reportSets;
    }

    @Override
    public String toString()
    {
        return gav.toString();
    }

    public Plugin toPlugin()
    {
        Plugin plugin = new Plugin();
        if ( gav.groupId != null )
        {
            plugin.setGroupId( gav.groupId );
        }
        if ( gav.artifactId != null )
        {
            plugin.setArtifactId( gav.artifactId );
        }
        if ( gav.version != null )
        {
            plugin.setVersion( gav.version );
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

    public static List<Plugin> toPlugins( List<ImmPlugin> plugins )
    {
        if ( plugins == null )
        {
            return null;
        }
        List<Plugin> result = new ArrayList<>();
        for ( ImmPlugin plugin : plugins )
        {
            result.add( plugin.toPlugin() );
        }
        return result;
    }

    public static PluginManagement toPluginManagement( List<ImmPlugin> plugins )
    {
        PluginManagement pluginManagement = new PluginManagement();
        pluginManagement.setPlugins( toPlugins( plugins ) );
        return pluginManagement;
    }

}
