package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Contributor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImmContributor
{
    private final String name;

    private final String email;

    private final String url;

    private final String organization;

    private final String organizationUrl;

    private final String timeZone;

    private final List<String> roles;

    private final Properties properties;

    public ImmContributor( String name, String email, String url, String organization, String organizationUrl,
                           String timeZone, List<String> roles, Properties properties )
    {
        this.name = name;
        this.email = email;
        this.url = url;
        this.organization = organization;
        this.organizationUrl = organizationUrl;
        this.timeZone = timeZone;
        this.roles = roles;
        this.properties = properties;
    }

    void setContributorFields(Contributor result)
    {
        result.setName( name );
        result.setEmail( email );
        result.setOrganization( organization );
        result.setProperties( properties );
        result.setRoles( roles );
        result.setOrganizationUrl( organizationUrl );
        result.setTimezone( timeZone );
        result.setUrl( url );
    }

    public Contributor toContributor()
    {
        Contributor result = new Contributor();
        setContributorFields(  result );
        return result;
    }

    public static List<Contributor> asContributorList( Iterable<ImmContributor> cont ){
        List<Contributor> result = new ArrayList<>(  );
        for ( ImmContributor immContributor : cont )
        {
            result.add( immContributor.toContributor());
        }
        return result;
    }
}
