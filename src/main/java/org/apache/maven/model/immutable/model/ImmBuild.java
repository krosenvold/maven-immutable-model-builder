package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Build;
import org.apache.maven.model.BuildBase;
import org.apache.maven.model.Extension;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.immutable.ModelElement;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ImmBuild
    extends ModelElement

{
    private final ImmList<ImmPlugin> plugins;  // PluginContainer

    private final ImmList<ImmPlugin> pluginManagement; // PluginConfiguration

    private String sourceDirectory;
    private String scriptSourceDirectory;
    private String testSourceDirectory;
    private String outputDirectory;
    private String testOutputDirectory;
    private ImmList<ImmExtension> extensions;
    private String defaultGoal;    // BuildBase
    private ImmList<ImmResource> resources;   // BuildBase
    private ImmList<ImmResource> testResources;  // BuildBase
    private String directory;  // BuildBase
    private String finalName;  // BuildBase
    private ImmList<String> filters;   // BuildBase


    public ImmBuild( ImmList<ImmPlugin> plugins, ImmList<ImmPlugin> pluginManagement, String directory,
                     String outputDirectory, String finalName, String testOutputDirectory, String sourceDirectory,
                     String scriptSourceDirectory, String testSourceDirectory, ImmList<ImmResource> resources,
                     ImmList<ImmResource> testResources, ImmList<ImmExtension> extensions, String defaultGoal,
                     ImmList<String> filters )
    {
        this.plugins = plugins;
        this.pluginManagement = pluginManagement;
        this.directory = directory;
        this.outputDirectory = outputDirectory;
        this.finalName = finalName;
        this.testOutputDirectory = testOutputDirectory;
        this.sourceDirectory = sourceDirectory;
        this.scriptSourceDirectory = scriptSourceDirectory;
        this.testSourceDirectory = testSourceDirectory;
        this.resources = resources;
        this.testResources = testResources;
        this.extensions = extensions;
        this.defaultGoal = defaultGoal;
        this.filters = filters;
    }

    public ImmList<ImmPlugin> getPlugins()
    {
        return plugins;
    }

    public void setBaseAttrs(BuildBase result)
    {
        result.setPlugins(plugins.toList(  ImmPlugin.mapper ));
        result.setPluginManagement( ImmPlugin.toPluginManagement( pluginManagement ) );
        result.setDefaultGoal( defaultGoal );
        result.setDirectory(  directory);
        if (filters != null) result.setFilters( filters.toList( ImmList.stringMapper ) );
        result.setFinalName( finalName );
        if (resources != null) result.setResources( resources.toList( ImmResource.mapper ));
        if (testResources != null)  result.setTestResources( testResources.toList( ImmResource.mapper ) );
    }

    public Build toBuild()
    {
        final Build result = new Build();
        setBaseAttrs( result );
        plugins.setList( new Function<ImmPlugin, Plugin>()
        {
            @Override
            public Plugin apply( ImmPlugin immPlugin )
            {
                return immPlugin.toPlugin();
            }
        }, new Consumer<List<Plugin>>()
        {
            @Override
            public void accept( List<Plugin> plugins )
            {
                result.setPlugins( plugins );
            }
        } );
        final List<Plugin> plugpmins = pluginManagement.toList( new Function<ImmPlugin, Plugin>()
        {
            @Override
            public Plugin apply( ImmPlugin immPlugin )
            {
                return immPlugin.toPlugin();
            }
        } );

        PluginManagement pm = new PluginManagement();
        pm.setPlugins( plugpmins );
        result.setPluginManagement( pm );

        if (extensions != null) result.setExtensions( extensions.toList( ImmExtension.mapper ));
        result.setOutputDirectory( outputDirectory );
        result.setScriptSourceDirectory(  scriptSourceDirectory);
        result.setSourceDirectory(  sourceDirectory);
        result.setTestOutputDirectory(  testOutputDirectory);
        result.setTestSourceDirectory(  testSourceDirectory);
        return result;
    }
}
