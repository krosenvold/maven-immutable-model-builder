package org.apache.maven.model.immutable.model;

public class ImmScm
{
    private final String connection;

    private final String developerConnection;

    private final String url;

    private final String tag;


    public ImmScm( String connection, String developerConnection, String url, String tag )
    {

        this.connection = connection;
        this.developerConnection = developerConnection;
        this.url = url;
        this.tag = tag;
    }
}
