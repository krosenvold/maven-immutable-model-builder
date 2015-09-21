package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ArtifactIdBuilder
    extends LeafBuilder
{
    public ArtifactId build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        return new ArtifactId( singleTextValue( node ) );
    }

}
