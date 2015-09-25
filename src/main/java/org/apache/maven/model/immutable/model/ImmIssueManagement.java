package org.apache.maven.model.immutable.model;

public class ImmIssueManagement
{
    private final String connection;

    private final String url;

    public ImmIssueManagement( String connection, String url )
    {

        this.connection = connection;
        this.url = url;
    }
}
