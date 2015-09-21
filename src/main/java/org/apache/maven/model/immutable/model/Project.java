package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class Project
    extends ModelElement
{
    private final Iterable<ModelElement> kids;

    public Project( Iterable<ModelElement> kids )
    {

        this.kids = kids;
    }
}
