package org.apache.maven.model.immutable.model;

import org.apache.maven.model.BuildBase;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.immutable.ModelElement;

import java.util.List;

/**
 * Created by kristian on 20.09.15.
 */
public class ImmBuild
    extends ModelElement

{
    private final ImmPlugins plugins;

    private final ImmPlugins pluginManagement;

    public ImmBuild( ImmPlugins plugins, ImmPlugins pluginManagement )
    {
        this.plugins = plugins;
        this.pluginManagement = pluginManagement;
    }

    public ImmPlugins getPlugins()
    {
        return plugins;
    }

    public BuildBase toBuild()
    {
        BuildBase buildBase = new BuildBase();
        buildBase.setPlugins( plugins.toPlugins() );
        buildBase.setPluginManagement( pluginManagement.toPluginManagement() );
        return null;
    }

    public List<Plugin> toPlugins()
    {
        return plugins.toPlugins();
    }

    public List<Plugin> toPluginManagement()
    {
        return pluginManagement.toPlugins();
    }
}
