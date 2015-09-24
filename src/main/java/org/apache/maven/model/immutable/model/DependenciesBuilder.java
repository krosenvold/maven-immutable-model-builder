package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

class DependenciesBuilder
{
    private final DependencyBuilder dependencyBuilder = new DependencyBuilder();


    public List<Dependency> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<Dependency> plugins = new ArrayList<>();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "dependency".equals( node.getLocalName() ) )
                    {
                        plugins.add( dependencyBuilder.build( node ) );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }

            }
        }
        return plugins;
    }
}
