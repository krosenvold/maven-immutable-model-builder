package org.apache.maven.model.immutable;

import org.apache.maven.model.immutable.model.Build;
import org.apache.maven.model.immutable.model.RootBuilder;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.InputStream;

public class ImmutableModelBuilder
{

    public Build buildModel( InputStream source )
        throws XMLStreamException
    {
        XMLInputFactory2 xmlInputFactory = (XMLInputFactory2) XMLInputFactory.newInstance();
        XMLStreamReader2 streamReader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader( source );
        RootBuilder rb = new RootBuilder();
        Build build = rb.build( streamReader );
        return build;
    }

}
