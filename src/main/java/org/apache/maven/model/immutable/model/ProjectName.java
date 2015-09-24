package org.apache.maven.model.immutable.model;

/**
 * Created by kristian on 24.09.15.
 */
public class ProjectName
{

    private final String name;

    public ProjectName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
