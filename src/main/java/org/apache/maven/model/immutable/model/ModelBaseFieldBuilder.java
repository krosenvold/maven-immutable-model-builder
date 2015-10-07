package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ModelBaseFieldBuilder
{
    private final ReportingBuilder reportingBuilder = new ReportingBuilder();


    private final PropertiesBuilder propertiesBuilder = new PropertiesBuilder();

    private final GenericImmListBuilder<String> modulesBuilder = new GenericImmListBuilder<>( "module", new LeafBuilder() );

    private final GenericImmListBuilder<ImmDependency> dependenciesBuilder =
        new GenericImmListBuilder<>( "dependency", new DependencyBuilder() );

    private final DistributionManagementBuilder distributionManagementBuilder = new DistributionManagementBuilder();

    private final RepositoryBuilder rp = new RepositoryBuilder();

    private final GenericImmListBuilder<ImmRepository> pluginRep = new GenericImmListBuilder<>( "pluginRepository", rp );

    private final GenericImmListBuilder<ImmRepository> repositoryBuilder = new GenericImmListBuilder<>( "repository", rp );

    private final DependencyManagementBuilder dependencyManagementBuilder =
        new DependencyManagementBuilder( dependenciesBuilder );


    public boolean build( XMLStreamReader2 node, ModelBaseState modelBaseState )
        throws XMLStreamException
    {
        int eventType = node.getEventType();
        switch ( eventType )
        {
            case XMLStreamReader2.START_ELEMENT:
                String localName = node.getLocalName();
                switch ( localName )
                {
                    case "reporting":
                        modelBaseState.reporting = reportingBuilder.build( node );
                        return true;
                    case "properties":
                        modelBaseState.properties = propertiesBuilder.build( node );
                        return true;
                    case "modules":
                        modelBaseState.modules = modulesBuilder.build( node );
                        return true;
                    case "dependencyManagement":
                        modelBaseState.dependencyManagement = dependencyManagementBuilder.build( node );
                        return true;
                    case "dependencies":
                        modelBaseState.dependencies = dependenciesBuilder.build( node );
                        return true;
                    case "distributionManagement":
                        modelBaseState.distributionManagement = distributionManagementBuilder.build( node );
                        return true;
                    case "repositories":
                        modelBaseState.repositories = repositoryBuilder.build( node );
                        return true;
                    case "pluginRepositories":
                        modelBaseState.pluginRepositories = pluginRep.build( node );
                        return true;
                }
        }
        return false;
    }
}
