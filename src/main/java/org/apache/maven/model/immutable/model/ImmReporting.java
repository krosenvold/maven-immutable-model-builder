package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Reporting;

import java.util.List;

public class ImmReporting
{
    private final List<ImmPlugin> plugins;

    public ImmReporting( List<ImmPlugin> plugins )
    {
        this.plugins = plugins;
    }

    public Reporting toReporting()
    {
        // TODO: FIX
        return null;
    }
}
