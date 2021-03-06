package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class DistributionManagementBuilder
    implements ItemBuilder<ImmDistributionManagement>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final SiteBuilder siteBuilder = new SiteBuilder();


    public final ImmDistributionManagement build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        ImmSite site = null;
        String downloadUrl = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {

                        case "site":
                            site = this.siteBuilder.build( node );
                            break;
                        case "downloadUrl":
                            downloadUrl = leafBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                    }
            }
        }
        return new ImmDistributionManagement( site, downloadUrl );

    }
}
