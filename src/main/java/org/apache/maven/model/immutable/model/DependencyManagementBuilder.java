package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

/**
 * Created by kristian on 24.09.15.
 */
class DependencyManagementBuilder
{

    private final GenericListBuilder<ImmDependency> dependenciesBuilder;

    public DependencyManagementBuilder( GenericListBuilder<ImmDependency> dependenciesBuilder )
    {
        this.dependenciesBuilder = dependenciesBuilder;
    }


    public List<ImmDependency> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "dependencies".equals( node.getLocalName() ) )
                    {
                        return dependenciesBuilder.build( node );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }

            }
        }
        return null;
    }
}
