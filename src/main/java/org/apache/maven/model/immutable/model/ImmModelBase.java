package org.apache.maven.model.immutable.model;

import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.ModelBase;

import java.util.List;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ImmModelBase
{
    private final List<ImmDependency> dependencies;

    private final List<ImmDependency> dependencyManagement;

    private final ImmReporting reporting;

    private final List<String> modules;

    private final ImmDistributionManagement distributionManagement;

    public ImmModelBase( ModelBaseState modelBaseState )
    {
        this.dependencies = modelBaseState.dependencies;
        this.dependencyManagement = modelBaseState.dependencyManagement;
        this.reporting = modelBaseState.reporting;
        modules = modelBaseState.modules;
        distributionManagement = modelBaseState.distributionManagement;
    }

    protected void setModelBase( ModelBase modelBase )
    {
        DependencyManagement dependencyManagement1 = new DependencyManagement();
        dependencyManagement1.setDependencies( ImmDependency.asList( dependencyManagement ) );
        modelBase.setDependencyManagement( dependencyManagement1 );
        modelBase.setDependencies( ImmDependency.asList( dependencies ) );
        modelBase.setModules( this.modules );
        if ( distributionManagement != null )
        {
            modelBase.setDistributionManagement( distributionManagement.toDistributionManagement() );
        }
        if ( reporting != null )
        {
            modelBase.setReporting( reporting.toReporting() );
        }
        // modelBase.setPluginRepositories(  ); TODO
        // modelBase.setRepositories(  ); TODO
    }

}
