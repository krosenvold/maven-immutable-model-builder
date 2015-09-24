package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ContributorBuilder
    extends BaseBuilder
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    /*
    // TODO: FIX
    private List<String> roles;
    private String timezone;
    private Properties properties;
    private Map<Object, InputLocation> locations;

     */

    public Contributor build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        String name = null;
        String email = null;
        String url = null;
        String organization = null;
        String organizationUrl = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {

                        case "name":
                            name = this.leafBuilder.singleTextValue( node );
                            break;
                        case "email":
                            email = leafBuilder.singleTextValue( node ) ;
                        case "url":
                            url = leafBuilder.singleTextValue( node ) ;
                            break;
                        case "organization":
                            organization = leafBuilder.singleTextValue( node ) ;
                            break;
                        case "organizationUrl":
                            organizationUrl = leafBuilder.singleTextValue( node ) ;
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );
                    }
            }
        }
        return new Contributor( name, email, url, organization, organizationUrl );

    }
}
