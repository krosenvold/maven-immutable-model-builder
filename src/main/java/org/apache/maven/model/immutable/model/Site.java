package org.apache.maven.model.immutable.model;

public class Site
{
    private final String connection;

    private final String url;

    public Site( String connection, String url )
    {

        this.connection = connection;
        this.url = url;
    }
}
