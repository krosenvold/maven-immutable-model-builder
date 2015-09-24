package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

public class ProfileBuilder
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();


    private final BuildBuilder buildBuilder;

    private final ReportingBuilder reportingBuilder = new ReportingBuilder();

    private final ActivationBuilder activationBuilder = new ActivationBuilder();

    public ProfileBuilder( BuildBuilder build )
    {
        this.buildBuilder = build;
    }


    public Profile build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        Build build = null;
        String id = null;
        Reporting reporting = null;
        Activation activation = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "id":
                            id = groupIdBuilder.singleTextValue( node );
                            break;
                        case "build":
                            build = buildBuilder.build( node );
                            break;
                        case "reporting":
                            reporting = reportingBuilder.build( node );
                            break;
                        case "activation":
                            activation = activationBuilder.build( node );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag " + localName );
                    }
            }
        }

        return new Profile( id, build, reporting );
    }
}
