package org.apache.maven.model.immutable.model;

import org.apache.maven.model.RepositoryBase;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ImmRepositoryBase
{
    private final String id;

    private final String name;

    private final String url;

    private final String layout;


    public ImmRepositoryBase( String id, String name, String url, String layout )
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.layout = layout;
    }

    protected void setRepositoryBase( RepositoryBase base )
    {
        base.setId( id );
        base.setName( name );
        base.setUrl( url );
        base.setLayout( layout );
    }
}
