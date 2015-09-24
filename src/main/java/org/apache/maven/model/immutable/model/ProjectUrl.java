package org.apache.maven.model.immutable.model;

/**
 * Created by kristian on 24.09.15.
 */
public class ProjectUrl
{
    private final String url;

    public ProjectUrl( String url )
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
}
