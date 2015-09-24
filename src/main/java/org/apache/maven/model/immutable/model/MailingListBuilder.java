package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

/**
 * Created by kristian on 24.09.15.
 */
class MailingListBuilder
    extends BaseBuilder<MailingList>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    public MailingList build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String name = null;
        String post = null;
        String subscribe = null;
        String unsubscribe = null;

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
                            name = leafBuilder.singleTextValue( node );
                            break;
                        case "post":
                            post = leafBuilder.singleTextValue( node );
                            break;
                        case "subscribe":
                            subscribe = leafBuilder.singleTextValue( node );
                            break;
                        case "unsubscribe":
                            unsubscribe = leafBuilder.singleTextValue( node );
                            break;
                    }
            }
        }

        return new MailingList( name, post, subscribe, unsubscribe );
    }
}
