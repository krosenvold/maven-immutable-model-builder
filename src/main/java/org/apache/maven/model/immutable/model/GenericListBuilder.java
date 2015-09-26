package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

final class GenericListBuilder<T>
{
    private final String elementName;

    private final ItemBuilder<T> itemBuilder;

    public GenericListBuilder( String elementName, ItemBuilder<T> itemBuilder )
    {
        this.elementName = elementName;
        this.itemBuilder = itemBuilder;
    }

    public final List<T> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<T> result = new ArrayList<>();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( elementName.equals( node.getLocalName() ) )
                    {
                        result.add( itemBuilder.build( node ) );
                    }
                    else
                    {
                        throw new IllegalArgumentException(
                            "Unsupported tag " + node.getLocalName() + ", expected" + elementName );
                    }

            }
        }
        return result;
    }
}
