package org.apache.maven.model.immutable.model;

/**
 * Created by kristian on 24.09.15.
 */
public class ImmProjectName
{

    private final String name;

    public ImmProjectName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
