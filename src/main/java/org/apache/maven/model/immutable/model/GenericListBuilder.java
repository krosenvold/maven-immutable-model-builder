package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

class GenericListBuilder<T>
{
    private final String elementName;

    private final ItemBuilder<T> itemBuilder;

    public GenericListBuilder( String elementName, ItemBuilder<T> itemBuilder )
    {
        this.elementName = elementName;
        this.itemBuilder = itemBuilder;
    }

    public List<T> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<T> result = new ArrayList<>();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( elementName.equals( localName ) )
                    {
                        result.add( itemBuilder.build( node ) );
                    }
                    else
                    {
                        throw new IllegalArgumentException(
                            "Unsupported tag " + localName + ", expected" + elementName );
                    }

            }
        }
        return result;
    }
}
