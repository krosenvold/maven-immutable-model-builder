package org.apache.maven.model.immutable.model;

public class ImmContributor
{
    private final String name;

    private final String email;

    private final String url;

    private final String organization;

    private final String organizationUrl;

    public ImmContributor( String name, String email, String url, String organization, String organizationUrl,
                           String timeZone )
    {

        this.name = name;
        this.email = email;
        this.url = url;
        this.organization = organization;
        this.organizationUrl = organizationUrl;
    }
}