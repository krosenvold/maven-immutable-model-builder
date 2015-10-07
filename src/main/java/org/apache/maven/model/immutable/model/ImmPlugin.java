package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.plexus.util.StringInputStream;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ImmPlugin
    extends ModelElement
{
    private final Gav gav;

    private final StringBuilder configuration;

    private final StringBuilder reportSets;


    public ImmPlugin( Gav gav, StringBuilder configuration, StringBuilder reportSets )
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
            if (configuration != null)
            {
                String payload = configuration.toString();
                if ( !StringUtils.isEmpty( payload ) ) plugin.setConfiguration( Xpp3DomBuilder.build( new StringInputStream( payload ), "UTF-8" ) );
            }
        }
        catch ( XmlPullParserException | IOException e )
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

    public static PluginManagement toPluginManagement( ImmList<ImmPlugin> plugins )
    {
        PluginManagement pluginManagement = new PluginManagement();
        pluginManagement.setPlugins( plugins.toList(  mapper) );
        return pluginManagement;
    }

    static final Function<ImmPlugin, Plugin> mapper = new Function<ImmPlugin, Plugin>()
    {
        @Override
        public Plugin apply( ImmPlugin immPlugin )
        {
            return immPlugin.toPlugin();
        }
    };

}
