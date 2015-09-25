package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ProfileBuilder
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();


    private final BuildBuilder buildBuilder;

    private final ReportingBuilder reportingBuilder;

    private final ActivationBuilder activationBuilder = new ActivationBuilder();

    public ProfileBuilder( BuildBuilder build, ReportingBuilder reportingBuilder )
    {
        this.buildBuilder = build;
        this.reportingBuilder = reportingBuilder;
    }


    public ImmProfile build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        ImmBuild build = null;
        String id = null;
        ImmReporting reporting = null;
        ImmActivation activation = null;

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

        return new ImmProfile( id, build, reporting );
    }
}
