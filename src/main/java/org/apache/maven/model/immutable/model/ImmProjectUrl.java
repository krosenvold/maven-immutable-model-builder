package org.apache.maven.model.immutable.model;

/**
 * Created by kristian on 24.09.15.
 */
public class ImmProjectUrl
{
    private final String url;

    public ImmProjectUrl( String url )
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
}
