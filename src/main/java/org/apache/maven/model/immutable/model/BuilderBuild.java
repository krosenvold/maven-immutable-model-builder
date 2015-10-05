package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class BuilderBuild
{

    private final GenericListBuilder<ImmPlugin> pluginsBuilder =
        new GenericListBuilder<>( "plugin", new PluginBuilder() );

    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final ResourceBuilder rb = new ResourceBuilder();

    private final GenericListBuilder<ImmResource> resourcesBuilder =
        new GenericListBuilder<>( "resource", rb);

    private final GenericListBuilder<ImmResource> testResourcesBuilder =
        new GenericListBuilder<>( "testResource", rb);

    private final GenericListBuilder<ImmExtension> extensionsBuilder =
        new GenericListBuilder<>( "extension", new ExtensionBuilder());

    private final GenericListBuilder<String> filtersBuilder =
        new GenericListBuilder<>( "filter", leafBuilder);

    private final PluginManagementBuilder pluginManagementBuilder = new PluginManagementBuilder( pluginsBuilder );

    private ImmList<ImmExtension> extensions;


    public ImmBuild build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        ImmList<ImmPlugin> plugins = null;
        ImmList<ImmPlugin> pluginManagement = null;

        String directory = null;
        String outputDirectory = null;
        String finalName = null;
        String testOutputDirectory = null;
        String sourceDirectory = null;
        String scriptSourceDirectory = null;
        String testSourceDirectory = null;
        ImmList<ImmResource> resources = null;
        ImmList<ImmResource> testResources = null;
        String defaultGoal = null;
        ImmList<String> filters = null;
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {
                        case "directory":
                            directory = leafBuilder.build( node );
                            break;
                        case "outputDirectory":
                            outputDirectory = leafBuilder.build( node );
                            break;
                        case "defaultGoal":
                            defaultGoal = leafBuilder.build( node );
                            break;
                        case "finalName":
                            finalName = leafBuilder.build( node );
                            break;
                        case "testOutputDirectory":
                            testOutputDirectory = leafBuilder.build( node );
                            break;
                        case "sourceDirectory":
                            sourceDirectory = leafBuilder.build( node );
                            break;
                        case "scriptSourceDirectory":
                            scriptSourceDirectory = leafBuilder.build( node );
                            break;
                        case "testSourceDirectory":
                            testSourceDirectory = leafBuilder.build( node );
                            break;
                        case "plugins":
                            plugins =  ImmList.of(pluginsBuilder.build( node ));
                            break;
                        case "resources":
                            resources = ImmList.of( resourcesBuilder.build( node ) );
                            break;
                        case "testResources":
                            testResources = ImmList.of( testResourcesBuilder.build( node ) );
                            break;
                        case "pluginManagement":
                            pluginManagement = ImmList.of( pluginManagementBuilder.build( node ));
                            break;
                        case "extensions":
                            extensions = ImmList.of( extensionsBuilder.build( node ) );
                            break;
                        case "filters":
                            filters = ImmList.of( filtersBuilder.build( node ));
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag" + node.getLocalName() );

                    }
            }
        }
        return new ImmBuild( plugins, pluginManagement, directory, outputDirectory, finalName, testOutputDirectory,
                             sourceDirectory, scriptSourceDirectory, testSourceDirectory, resources, testResources,
                             extensions, defaultGoal, filters );

    }
}
