package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Site;

public class ImmSite
{
    private final String id;

    private final String url;

    private final String name;

    public ImmSite( String id, String url, String name )
    {

        this.id = id;
        this.url = url;
        this.name = name;
    }

    public Site toSite()
    {
        Site site = new Site();
        site.setId( id );
        site.setUrl( url );
        site.setName( name );
        return site;
    }
}
