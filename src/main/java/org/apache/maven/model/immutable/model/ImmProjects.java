package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class ImmProjects
    extends ModelElement
{
    private final Iterable<ModelElement> kids;

    public ImmProjects( Iterable<ModelElement> kids )
    {

        this.kids = kids;
    }
}
