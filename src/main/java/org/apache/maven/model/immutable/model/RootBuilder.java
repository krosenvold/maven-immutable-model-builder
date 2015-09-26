package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class RootBuilder
{
    private final ProjectBuilder projectBuilder = new ProjectBuilder();

    public ImmProject build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        ImmProject build1 = null;
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( "project".equals( node.getLocalName() ) )
                    {
                        build1 = projectBuilder.build( node );
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
