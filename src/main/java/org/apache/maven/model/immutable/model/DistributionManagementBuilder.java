package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class DistributionManagementBuilder
    extends BaseBuilder
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final SiteBuilder siteBuilder = new SiteBuilder();


    public ImmDistributionManagement build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        ImmSite site = null;
        String downloadUrl = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {

                        case "site":
                            site = this.siteBuilder.build( node );
                            break;
                        case "downloadUrl":
                            downloadUrl = leafBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );
                    }
            }
        }
        return new ImmDistributionManagement( site, downloadUrl );

    }
}
