package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ModelVersionBuilder
    extends LeafBuilder
{
    public ModelVersion build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        return new ModelVersion( singleTextValue( node ) );
    }
}
