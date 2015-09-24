package org.apache.maven.model.immutable;

import org.apache.maven.model.immutable.model.Project;
import org.apache.maven.model.immutable.model.RootBuilder;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;

class ImmutableModelBuilder
{

    XMLInputFactory2 xmlInputFactory = (XMLInputFactory2) XMLInputFactory.newInstance();

    public Project buildModel( InputStream source )
        throws XMLStreamException
    {
        XMLStreamReader2 streamReader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader( source );
        RootBuilder rb = new RootBuilder();
        return rb.build( streamReader );
    }

    public Project readProject( String name )
        throws XMLStreamException, IOException
    {
        InputStream resourceAsStream2 = this.getClass().getResourceAsStream( name );
        Project project = buildModel( resourceAsStream2 );
        resourceAsStream2.close();
        return project;
    }


}
