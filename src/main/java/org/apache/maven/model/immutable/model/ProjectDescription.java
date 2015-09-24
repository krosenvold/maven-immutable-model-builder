package org.apache.maven.model.immutable.model;

/**
 * Created by kristian on 24.09.15.
 */
public class ProjectDescription
{
    private final String description;

    public ProjectDescription( String description )
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
}
