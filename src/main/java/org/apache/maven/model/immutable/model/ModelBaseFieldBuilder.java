package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class ModelBaseFieldBuilder
    extends BaseBuilder
{
    private final ReportingBuilder reportingBuilder = new ReportingBuilder();


    private final PropertiesBuilder propertiesBuilder = new PropertiesBuilder();

    private final ModulesBuilder modulesBuilder = new ModulesBuilder();

    private final DependenciesBuilder dependenciesBuilder = new DependenciesBuilder();

    private final DistributionManagementBuilder distributionManagementBuilder = new DistributionManagementBuilder();

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
                    // TODO Repositories
                    // TODO pluginRepositories
                }
        }
        return false;
    }
}
