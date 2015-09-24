package org.apache.maven.model.immutable.model;

public class CiManagement
{
    private final String connection;

    private final String url;

    public CiManagement( String connection, String url )
    {

        this.connection = connection;
        this.url = url;
    }
}
