package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Contributor;
import org.apache.maven.model.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

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

    static final Function<ImmContributor, Contributor> mapper = new Function<ImmContributor, Contributor>()
    {
        @Override
        public Contributor apply( ImmContributor contributor )
        {
            return contributor.toContributor();
        }
    };


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
}
