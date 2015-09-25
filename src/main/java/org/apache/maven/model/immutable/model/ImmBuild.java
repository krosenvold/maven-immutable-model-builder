package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class ImmBuild
    extends ModelElement

{
    private final ImmPlugins plugins;

    public ImmBuild( ImmPlugins plugins, ImmPlugins pluginManagement )
    {
        this.plugins = plugins;
    }

    public ImmPlugins getPlugins()
    {
        return plugins;
    }
}
