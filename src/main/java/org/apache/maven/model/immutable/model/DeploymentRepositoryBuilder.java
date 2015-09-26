package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class DeploymentRepositoryBuilder
    implements ItemBuilder<ImmDeploymentRepository>
{
    private final LeafBuilder leafBuilder = new LeafBuilder();


    public ImmDeploymentRepository build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String uniqueVersion = null;
        String id = null;
        String layout = null;
        String url = null;
        String name = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {
                        case "uniqueVersion":
                            uniqueVersion = leafBuilder.build( node );
                            break;
                        case "id":
                            id = leafBuilder.build( node );
                            break;
                        case "name":
                            name = leafBuilder.build( node );
                            break;
                        case "url":
                            url = leafBuilder.build( node );
                            break;
                        case "layout":
                            layout = leafBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag" + node.getLocalName() );

                    }
            }
        }
        return new ImmDeploymentRepository( id, name, url, layout, uniqueVersion );
    }
}