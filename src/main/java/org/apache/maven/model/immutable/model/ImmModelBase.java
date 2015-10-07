package org.apache.maven.model.immutable.model;

import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.ModelBase;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ImmModelBase
{
    private final ImmList<ImmDependency> dependencies;

    private final ImmList<ImmDependency> dependencyManagement;

    private final ImmReporting reporting;

    private final ImmList<String> modules;

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
        dependencyManagement1.setDependencies( dependencyManagement.toList( ImmDependency.mapper ));
        modelBase.setDependencyManagement( dependencyManagement1 );
        modelBase.setDependencies( dependencies.toList(  ImmDependency.mapper ));
        modelBase.setModules( this.modules.toList( ImmList.stringMapper ) );
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
