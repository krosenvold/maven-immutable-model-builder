package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public interface ItemBuilder<T>
{
    T build( XMLStreamReader2 node )
        throws XMLStreamException;
}
