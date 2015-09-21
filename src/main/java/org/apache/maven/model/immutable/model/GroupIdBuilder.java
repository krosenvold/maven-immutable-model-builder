package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class GroupIdBuilder
    extends LeafBuilder
{
    public GroupId build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        return new GroupId( singleTextValue( node ) );
    }

}
