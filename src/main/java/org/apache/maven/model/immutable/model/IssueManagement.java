package org.apache.maven.model.immutable.model;

public class IssueManagement
{
    private final String connection;

    private final String url;

    public IssueManagement( String connection, String url )
    {

        this.connection = connection;
        this.url = url;
    }
}
