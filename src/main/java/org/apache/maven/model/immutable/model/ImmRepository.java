package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Repository;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ImmRepository
    extends ImmRepositoryBase
{
    private final ImmRepositoryPolicy releases;

    private final ImmRepositoryPolicy snapshots;

    public ImmRepository( String id, String name, String url, String layout, ImmRepositoryPolicy releases,
                          ImmRepositoryPolicy snapshots )
    {
        super( id, name, url, layout );
        this.releases = releases;
        this.snapshots = snapshots;
    }

    public Repository toRepository()
    {
        Repository repository = new Repository();
        super.setRepositoryBase( repository );
        repository.setReleases( releases.toRepositoryPolicy() );
        repository.setSnapshots( snapshots.toRepositoryPolicy() );
        return repository;
    }
}
