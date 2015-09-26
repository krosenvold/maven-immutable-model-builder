package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ParentBuilder
{
    private final LeafBuilder rp = new LeafBuilder();

    private final GavFieldBuilder gavFieldBuilder = new GavFieldBuilder();


    public ImmParent build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String relativePath = null;

        GavState gavState = new GavState();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( !gavFieldBuilder.build( node, gavState ) )
                    {

                        switch ( node.getLocalName() )
                        {
                            case "relativePath":
                                relativePath = rp.build( node );
                                break;
                            default:
                                throw new IllegalArgumentException( "Unsupported tag " + node.getLocalName() );
                        }
                    }
            }

        }
        return new ImmParent( relativePath, gavState.gav() );
    }
}
