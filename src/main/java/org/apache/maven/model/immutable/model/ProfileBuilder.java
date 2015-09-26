package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

class ProfileBuilder
    implements ItemBuilder<ImmProfile>
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();


    private final BuildBuilder buildBuilder;

    private final ReportingBuilder reportingBuilder;

    private final ActivationBuilder activationBuilder = new ActivationBuilder();

    private final DependenciesBuilder dependenciesBuilder = new DependenciesBuilder();

    private final ModelBaseFieldBuilder modelBaseFieldBuilder = new ModelBaseFieldBuilder();

    private final DependencyManagementBuilder dependencyManagementBuilder =
        new DependencyManagementBuilder( dependenciesBuilder );

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
        List<ImmDependency> dependencyManagement = null;
        List<ImmDependency> dependencies = null;

        ModelBaseState mbState = new ModelBaseState();


        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( !modelBaseFieldBuilder.build( node, mbState ) )
                    {
                        switch ( localName )
                        {
                            case "id":
                                id = groupIdBuilder.build( node );
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
                            case "dependencyManagement":
                                dependencyManagement = dependencyManagementBuilder.build( node );
                                break;
                            case "dependencies":
                                dependencies = dependenciesBuilder.build( node );
                                break;

                            default:
                                throw new RuntimeException( "Unsupported child tag " + localName );
                        }
                    }
            }
        }

        return new ImmProfile( mbState, id, activation, build, reporting, dependencies, dependencyManagement );
    }
}
