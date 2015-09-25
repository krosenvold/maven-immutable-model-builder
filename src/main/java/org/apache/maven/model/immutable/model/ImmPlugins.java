package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

import java.util.List;

public class ImmPlugins
    extends ModelElement
{
    private final List<ImmPlugin2> plugins;

    public ImmPlugins( List<ImmPlugin2> plugins )
    {
        this.plugins = plugins;
    }

    public int size()
    {
        return plugins.size();
    }

    public List<ImmPlugin2> getPlugins()
    {
        return plugins;
    }
}
