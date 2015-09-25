package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

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
}
