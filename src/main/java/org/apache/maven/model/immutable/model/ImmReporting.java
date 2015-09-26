package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Reporting;

/**
 * Created by kristian on 24.09.15.
 */
public class ImmReporting
{
    private final ImmPlugins plugins;

    public ImmReporting( ImmPlugins plugins )
    {
        this.plugins = plugins;
    }

    public Reporting toReporting()
    {
        return null;
    }
}
