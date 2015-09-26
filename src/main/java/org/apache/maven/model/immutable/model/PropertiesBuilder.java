package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.Properties;

class PropertiesBuilder
{
    private final LeafBuilder propVal = new LeafBuilder();


    public Properties build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        // Todo: are props list or map ?
        Properties props = new Properties(  );

        int startLevel = node.getDepth();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    final String groupId1 = propVal.build( node );
                    props.put(  localName, groupId1 );
            }
        }


        return props;
    }
}
