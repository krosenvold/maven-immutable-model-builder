package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristian on 24.09.15.
 */
class ContributorsBuilder
    extends BaseBuilder

{
    private final ContributorBuilder listBuilder = new ContributorBuilder();


    public List<Contributor> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<Contributor> result = new ArrayList<>();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "contributor".equals( node.getLocalName() ) )
                    {
                        result.add( listBuilder.build( node ) );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }

            }
        }
        return result;
    }
}