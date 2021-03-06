package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class IssueManagementBuilder
    implements ItemBuilder<ImmIssueManagement>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public final ImmIssueManagement build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        String connection = null;
        String url = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {

                        case "system":
                            connection = this.leafBuilder.build( node );
                            break;
                        case "url":
                            url = leafBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                    }
            }
        }
        return new ImmIssueManagement( connection, url );

    }
}
