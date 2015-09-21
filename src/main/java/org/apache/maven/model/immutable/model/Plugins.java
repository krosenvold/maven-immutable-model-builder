package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class Plugins
    extends ModelElement
{
    private final Iterable<ModelElement> plugins;


    public Plugins( Iterable<ModelElement> plugins )
    {
        this.plugins = plugins;
    }
}
