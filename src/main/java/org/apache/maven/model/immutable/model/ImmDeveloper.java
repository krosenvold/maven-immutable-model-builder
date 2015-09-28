package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Contributor;
import org.apache.maven.model.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImmDeveloper
    extends ImmContributor
{
    private final String id;

    public ImmDeveloper( String name, String email, String url, String organization, String organizationUrl,
                         String timeZone, List<String> roles, Properties properties, String id )
    {
        super( name, email, url, organization, organizationUrl, timeZone, roles, properties );
        this.id = id;
    }

    public Developer toDeveloper()
    {
        Developer result = new Developer();
        result.setId( id );
        setContributorFields( result );
        return result;
    }

    public static List<Developer> asDeveloperList(Iterable<ImmDeveloper> cont){
        List<Developer> result = new ArrayList<>(  );
        for ( ImmDeveloper immContributor : cont )
        {
            result.add( immContributor.toDeveloper());
        }
        return result;
    }

}
