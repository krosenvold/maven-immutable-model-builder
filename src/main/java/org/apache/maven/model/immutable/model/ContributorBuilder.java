package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.Properties;

class ContributorBuilder
    implements ItemBuilder<ImmContributor>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final GenericListBuilder<String> roleBuilder =
        new GenericListBuilder<>( "role", new LeafBuilder() );

    private final PropertiesBuilder propertiesBuilder = new PropertiesBuilder();

    public final ImmContributor build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        String name = null;
        String email = null;
        String url = null;
        String organization = null;
        String organizationUrl = null;
        String timeZone = null;
        List<String> roles = null;

        Properties properties = null;
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {

                        case "name":
                            name = this.leafBuilder.build( node );
                            break;
                        case "email":
                            email = leafBuilder.build( node );
                        case "url":
                            url = leafBuilder.build( node );
                            break;
                        case "organization":
                            organization = leafBuilder.build( node );
                            break;
                        case "organizationUrl":
                            organizationUrl = leafBuilder.build( node );
                            break;
                        case "timezone":
                            timeZone = leafBuilder.build( node );
                            break;
                        case "roles":
                            roles = roleBuilder.build( node );
                            break;
                        case "properties":
                            properties = propertiesBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                    }
            }
        }
        return new ImmContributor( name, email, url, organization, organizationUrl, timeZone, roles, properties );

    }
}
