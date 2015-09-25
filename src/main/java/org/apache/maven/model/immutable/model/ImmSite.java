package org.apache.maven.model.immutable.model;

public class ImmSite
{
    private final String connection;

    private final String url;

    public ImmSite( String connection, String url )
    {

        this.connection = connection;
        this.url = url;
    }
}
