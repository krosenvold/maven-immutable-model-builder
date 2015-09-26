package org.apache.maven.model.immutable.model;

import org.apache.maven.model.BuildBase;
import org.apache.maven.model.immutable.ModelElement;

import java.util.List;

public class ImmBuild
    extends ModelElement

{
    private final List<ImmPlugin> plugins;

    private final List<ImmPlugin> pluginManagement;

    public ImmBuild( List<ImmPlugin> plugins, List<ImmPlugin> pluginManagement )
    {
        this.plugins = plugins;
        this.pluginManagement = pluginManagement;
    }

    public List<ImmPlugin> getPlugins()
    {
        return plugins;
    }

    public BuildBase toBuild()
    {
        BuildBase result = new BuildBase();
        result.setPlugins( ImmPlugin.toPlugins( plugins ) );
        result.setPluginManagement( ImmPlugin.toPluginManagement( pluginManagement ) );
        return result;
    }

}
