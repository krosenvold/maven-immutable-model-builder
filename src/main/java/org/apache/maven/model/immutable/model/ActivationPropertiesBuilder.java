package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ActivationPropertiesBuilder
{
    private final LeafBuilder propVal = new LeafBuilder();


    public ImmActivationProperty build( XMLStreamReader2 node )
        throws XMLStreamException
    {

        ImmActivationProperty immActivationProperty = null;
        int startLevel = node.getDepth();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    final String groupId1 = propVal.build( node );
                    immActivationProperty = new ImmActivationProperty( localName, groupId1 );
            }
        }

        return immActivationProperty;
    }
}
