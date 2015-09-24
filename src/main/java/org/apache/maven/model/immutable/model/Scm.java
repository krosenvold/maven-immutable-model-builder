package org.apache.maven.model.immutable.model;

public class Scm
{
    private final String connection;

    private final String developerConnection;

    private final String url;

    private final String tag;


    public Scm( String connection, String developerConnection, String url, String tag )
    {

        this.connection = connection;
        this.developerConnection = developerConnection;
        this.url = url;
        this.tag = tag;
    }
}
