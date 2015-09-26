package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

class ReportingBuilder
{

    private final GenericListBuilder<ImmPlugin> pluginsBuilder =
        new GenericListBuilder( "plugin", new PluginBuilder() );

    private final LeafBuilder leafBuilder = new LeafBuilder();

    public ImmReporting build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<ImmPlugin> build = null;

        String outputDirectory = null;
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "plugins":
                            build = pluginsBuilder.build( node );
                            break;

                        case "outputDirectory":
                            outputDirectory = leafBuilder.build( node );
                            break;
                        default:
                            throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }
            }
        }
        return new ImmReporting( build, outputDirectory );
    }

}
