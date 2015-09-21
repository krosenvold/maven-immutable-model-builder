package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class Build
    extends ModelElement

{
    private final Plugins plugins;

    public Build( Plugins plugins )
    {
        this.plugins = plugins;
    }
}
