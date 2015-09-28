package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

class CiManagementBuilder
    implements ItemBuilder<ImmCiManagement>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final GenericListBuilder<ImmNotifier> notifiersBuilder =
        new GenericListBuilder<>( "notifier", new NotifierBuilder() );


    public ImmCiManagement build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        String system = null;
        String url = null;

        List<ImmNotifier> notifiers = null;
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {

                        case "system":
                            system = this.leafBuilder.build( node );
                            break;
                        case "url":
                            url = leafBuilder.build( node );
                            break;
                        case "notifiers":
                            notifiers = notifiersBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                    }
            }
        }
        return new ImmCiManagement( system, url, notifiers );

    }
}
