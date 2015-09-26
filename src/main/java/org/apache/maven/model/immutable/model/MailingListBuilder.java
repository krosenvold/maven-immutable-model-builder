package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class MailingListBuilder
    implements ItemBuilder<ImmMailingList>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    public ImmMailingList build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String name = null;
        String post = null;
        String subscribe = null;
        String unsubscribe = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {
                        case "name":
                            name = leafBuilder.build( node );
                            break;
                        case "post":
                            post = leafBuilder.build( node );
                            break;
                        case "subscribe":
                            subscribe = leafBuilder.build( node );
                            break;
                        case "unsubscribe":
                            unsubscribe = leafBuilder.build( node );
                            break;
                        default:
                            throw new IllegalArgumentException( "Unsupported tag " + node.getLocalName() );

                    }
            }
        }

        return new ImmMailingList( name, post, subscribe, unsubscribe );
    }
}
