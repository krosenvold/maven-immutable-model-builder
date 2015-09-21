package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

import java.util.List;

public class Plugins
    extends ModelElement
{
    private final List<Plugin> plugins;

    public Plugins( List<Plugin> plugins )
    {
        this.plugins = plugins;
    }

    public int size()
    {
        return plugins.size();
    }

    public List<Plugin> getPlugins()
    {
        return plugins;
    }
}
