package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ModelBaseFieldBuilder
    extends BaseBuilder
{
    private final ReportingBuilder reportingBuilder = new ReportingBuilder();


    private final PropertiesBuilder propertiesBuilder = new PropertiesBuilder();

    private final GenericListBuilder<String> modulesBuilder = new GenericListBuilder<>( "module", new LeafBuilder() );

    private final GenericListBuilder<ImmDependency> dependenciesBuilder =
        new GenericListBuilder<>( "dependency", new DependencyBuilder() );

    private final DistributionManagementBuilder distributionManagementBuilder = new DistributionManagementBuilder();

    private final RepositoryBuilder rp = new RepositoryBuilder();

    private final GenericListBuilder<ImmRepository> pluginRep = new GenericListBuilder<>( "pluginRepository", rp );

    private final GenericListBuilder<ImmRepository> repositoryBuilder = new GenericListBuilder<>( "repository", rp );

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
