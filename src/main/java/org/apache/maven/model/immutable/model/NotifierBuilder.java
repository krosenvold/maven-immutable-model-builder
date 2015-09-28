package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.Properties;

public class NotifierBuilder
    implements ItemBuilder<ImmNotifier>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final XmlBuilder xmlBuilder = new XmlBuilder();


    private final GenericListBuilder<String> roleBuilder =
        new GenericListBuilder<>( "role", new LeafBuilder() );

    private final PropertiesBuilder propertiesBuilder = new PropertiesBuilder();

    public final ImmNotifier build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        String sendOnError = null;
        String sendOnFailure = null;
        String sendOnSuccess = null;
        String sendOnWarning = null;
        Properties configuration = null;
        String type = null;
        String address = null;
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {

                        case "type":
                            type = this.leafBuilder.build( node );
                            break;
                        case "address":
                            address = this.leafBuilder.build( node );
                            break;
                        case "sendOnError":
                            sendOnError = this.leafBuilder.build( node );
                            break;
                        case "sendOnFailure":
                            sendOnFailure = leafBuilder.build( node );
                        case "sendOnSuccess":
                            sendOnSuccess = leafBuilder.build( node );
                            break;
                        case "sendOnWarning":
                            sendOnWarning = leafBuilder.build( node );
                            break;
                        case "configuration":
                            configuration = propertiesBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                    }
            }
        }
        return new ImmNotifier( sendOnError, sendOnFailure, sendOnSuccess, sendOnWarning, configuration, type, address );

    }
}
