package org.apache.maven.model.immutable.model;

import org.apache.maven.model.DeploymentRepository;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ImmDeploymentRepository
    extends ImmRepositoryBase
{
    String uniqueVersion;

    public ImmDeploymentRepository( String id, String name, String url, String layout, String uniqueVersion )
    {
        super( id, name, url, layout );
    }

    public DeploymentRepository toDeploymentRepository()
    {
        DeploymentRepository result = new DeploymentRepository();
        setRepositoryBase( result );
        if ( uniqueVersion != null )
        {
            result.setUniqueVersion( Boolean.valueOf( uniqueVersion ) );
        }
        return result;
    }
}
