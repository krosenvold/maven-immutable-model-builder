package org.apache.maven.model.immutable;

import org.apache.maven.model.immutable.model.ImmModel;
import org.apache.maven.model.immutable.model.RootBuilder;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class ImmutableModelBuilder
{

    XMLInputFactory2 xmlInputFactory = (XMLInputFactory2) XMLInputFactory.newInstance();

    private ImmModel buildModel( InputStream source )
        throws XMLStreamException
    {
        XMLStreamReader2 streamReader = (XMLStreamReader2) xmlInputFactory.createXMLStreamReader( source );
        RootBuilder rb = new RootBuilder();
        return rb.build( streamReader );
    }

    public ImmModel readProject( String name )
        throws XMLStreamException, IOException
    {
        InputStream resourceAsStream2 = this.getClass().getResourceAsStream( name );
        ImmModel project = buildModel( resourceAsStream2 );
        resourceAsStream2.close();
        return project;
    }

    public ImmModel readProject( URL name )
        throws XMLStreamException, IOException
    {
        try ( InputStream resourceAsStream2 = name.openStream() )
        {
            ImmModel project = buildModel( new BufferedInputStream( resourceAsStream2 ) );
            return project;
        }
    }

}
