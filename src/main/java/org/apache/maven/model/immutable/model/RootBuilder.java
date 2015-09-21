package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class RootBuilder
{
    private final ProjectBuilder projectBuilder = new ProjectBuilder();

    private Project project;

    private int depth;


    public Build build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        Build build = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "project".equals( localName ) )
                    {
                        return projectBuilder.build( node );
                    }
                    else
                    {
                        throw new RuntimeException( "Unsupported child tag" + localName );
                    }
            }
        }
        return build;
    }
}
