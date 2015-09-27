package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class PluginBuilder
    implements ItemBuilder<ImmPlugin>
{
    private final GavFieldBuilder gavFieldBuilder = new GavFieldBuilder();

    XmlBuilder xmlBuilder = new XmlBuilder();


    public final ImmPlugin build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String configuration = null;
        String reportSets = null;

        GavState gavState = new GavState();
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( !gavFieldBuilder.build( node, gavState ) )
                    {
                        switch ( node.getLocalName() )
                        {
                            case "configuration":
                                configuration = xmlBuilder.build( node );
                                break;
                            case "reportSets":
                                reportSets = xmlBuilder.build( node );
                                break;
                        }
                    }
            }
        }

        return new ImmPlugin( gavState.gav(), configuration, reportSets );
    }
}
