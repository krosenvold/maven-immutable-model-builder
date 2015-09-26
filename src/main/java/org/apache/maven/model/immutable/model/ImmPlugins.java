package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.immutable.ModelElement;

import java.util.ArrayList;
import java.util.List;

public class ImmPlugins
    extends ModelElement
{
    private final List<ImmPlugin> plugins;

    public ImmPlugins( List<ImmPlugin> plugins )
    {
        this.plugins = plugins;
    }

    public int size()
    {
        return plugins.size();
    }

    public List<ImmPlugin> getPlugins()
    {
        return plugins;
    }

    public PluginManagement toPluginManagement()
    {
        return null;
    }

    public List<Plugin> toPlugins()
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
}
