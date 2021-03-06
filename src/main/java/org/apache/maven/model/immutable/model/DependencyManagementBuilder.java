package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

/**
 * Created by kristian on 24.09.15.
 */
class DependencyManagementBuilder
{

    private final GenericImmListBuilder<ImmDependency> dependenciesBuilder;

    public DependencyManagementBuilder( GenericImmListBuilder<ImmDependency> dependenciesBuilder )
    {
        this.dependenciesBuilder = dependenciesBuilder;
    }


    public ImmList<ImmDependency> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( "dependencies".equals( node.getLocalName() ) )
                    {
                        return dependenciesBuilder.build( node );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + node.getLocalName() );
                    }

            }
        }
        return null;
    }
}
