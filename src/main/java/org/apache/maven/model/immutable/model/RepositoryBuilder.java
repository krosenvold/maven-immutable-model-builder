package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class RepositoryBuilder
    implements ItemBuilder<ImmRepository>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final RepositoryPolicyBuilder repositoryPolicyBuilder = new RepositoryPolicyBuilder();


    public ImmRepository build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        ImmRepositoryPolicy releases = null;
        ImmRepositoryPolicy snapshots = null;
        String id = null;
        String layout = null;
        String url = null;
        String name = null;
        /*
    private List<Exclusion> exclusions;
    private Map<Object, InputLocation> locations;
         */

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "releases":
                            releases = repositoryPolicyBuilder.build( node );
                            break;
                        case "snapshots":
                            snapshots = repositoryPolicyBuilder.build( node );
                            break;
                        case "id":
                            id = leafBuilder.singleTextValue( node );
                            break;
                        case "name":
                            name = leafBuilder.singleTextValue( node );
                            break;
                        case "url":
                            url = leafBuilder.singleTextValue( node );
                            break;
                        case "layout":
                            layout = leafBuilder.singleTextValue( node );
                            break;
                    }
            }
        }

        return new ImmRepository( id, name, url, layout, releases, snapshots );
    }
}