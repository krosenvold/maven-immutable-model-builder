package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class LeafBuilder
{
    protected String singleTextValue( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String text = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    break;
                case XMLStreamReader2.END_ELEMENT:
                    return text;
                case XMLStreamReader2.CHARACTERS:
                    text = node.getText();
                    break;
            }
        }

        return text;
    }

}
