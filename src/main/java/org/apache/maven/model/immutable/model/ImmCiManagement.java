package org.apache.maven.model.immutable.model;

public class ImmCiManagement
{
    private final String connection;

    private final String url;

    public ImmCiManagement( String connection, String url )
    {

        this.connection = connection;
        this.url = url;
    }
}
