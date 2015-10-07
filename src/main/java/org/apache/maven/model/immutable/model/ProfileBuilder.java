package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ProfileBuilder
    implements ItemBuilder<ImmProfile>
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();


    private final BuilderBuild buildBuilder;

    private final ReportingBuilder reportingBuilder;

    private final BuilderActivation activationBuilder = new BuilderActivation();

    private final GenericImmListBuilder<ImmDependency> dependenciesBuilder =
        new GenericImmListBuilder<>( "dependency", new DependencyBuilder() );

    private final ModelBaseFieldBuilder modelBaseFieldBuilder = new ModelBaseFieldBuilder();

    private final DependencyManagementBuilder dependencyManagementBuilder =
        new DependencyManagementBuilder( dependenciesBuilder );

    public ProfileBuilder( BuilderBuild build, ReportingBuilder reportingBuilder )
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
        ImmList<ImmDependency> dependencyManagement = null;
        ImmList<ImmDependency> dependencies = null;

        ModelBaseState mbState = new ModelBaseState();


        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( !modelBaseFieldBuilder.build( node, mbState ) )
                    {
                        switch ( node.getLocalName() )
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
                                throw new RuntimeException( "Unsupported child tag " + node.getLocalName() );
                        }
                    }
            }
        }

        return new ImmProfile( mbState, id, activation, build, reporting, dependencies, dependencyManagement );
    }
}
