package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class Projects
    extends ModelElement
{
    private final Iterable<ModelElement> kids;

    public Projects( Iterable<ModelElement> kids )
    {

        this.kids = kids;
    }
}
