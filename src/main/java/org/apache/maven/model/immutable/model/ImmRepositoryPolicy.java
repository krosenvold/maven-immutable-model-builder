package org.apache.maven.model.immutable.model;

import org.apache.maven.model.RepositoryPolicy;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ImmRepositoryPolicy
{

    private final String enabled;

    private final String updatePolicy;

    private final String checksumPolicy;

    public ImmRepositoryPolicy( String enabled, String updatePolicy, String checksumPolicy )
    {

        this.enabled = enabled;
        this.updatePolicy = updatePolicy;
        this.checksumPolicy = checksumPolicy;
    }

    public RepositoryPolicy toRepositoryPolicy()
    {
        RepositoryPolicy result = new RepositoryPolicy();
        if ( enabled != null )
        {
            result.setEnabled( Boolean.valueOf( enabled ) );
        }
        result.setUpdatePolicy( updatePolicy );
        result.setChecksumPolicy( checksumPolicy );
        return result;
    }
}
