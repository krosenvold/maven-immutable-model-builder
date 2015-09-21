package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class VersionBuilder
    extends LeafBuilder
{
    public Version build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        return new Version( singleTextValue( node ) );
    }
}
