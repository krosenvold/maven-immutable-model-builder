package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

/**
 * Created by kristian on 24.09.15.
 */
class RepositoryPolicyBuilder
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public ImmRepositoryPolicy build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String checksumPolicy = null;
        String updatePolicy = null;
        String enabled = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "enabled":
                            enabled = leafBuilder.build( node );
                            break;
                        case "updatePolicy":
                            updatePolicy = leafBuilder.build( node );
                            break;
                        case "checksumPolicy":
                            checksumPolicy = leafBuilder.build( node );
                            break;
                    }
            }
        }

        return new ImmRepositoryPolicy( enabled, updatePolicy, checksumPolicy );
    }
}