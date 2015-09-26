package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

class DeploymentRepositoriesBuilder
{
    private final DeploymentRepositoryBuilder deploymentRepositoryBuilder = new DeploymentRepositoryBuilder();


    public List<ImmDeploymentRepository> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<ImmDeploymentRepository> repositories = new ArrayList<>();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "repository".equals( node.getLocalName() ) )
                    {
                        repositories.add( deploymentRepositoryBuilder.build( node ) );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }

            }
        }
        return repositories;
    }
}
