package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class RootBuilder
{
    private final ModelBuilder modelBuilder = new ModelBuilder();

    public ImmModel build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        ImmModel build1 = null;
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( "project".equals( node.getLocalName() ) )
                    {
                        build1 = modelBuilder.build( node );
                    }
                    else
                    {
                        throw new RuntimeException( "Unsupported child tag" + node.getLocalName() );
                    }
            }
        }
        return build1;
    }
}
