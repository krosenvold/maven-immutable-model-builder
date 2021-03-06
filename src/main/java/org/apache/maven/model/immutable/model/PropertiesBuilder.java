package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.Properties;

class PropertiesBuilder implements ItemBuilder<Properties>
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
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    final String groupId1 = propVal.build( node );
                    props.put( node.getLocalName(), groupId1 );
            }
        }
        return props;
    }
}
