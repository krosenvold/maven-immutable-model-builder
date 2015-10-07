package org.apache.maven.model.immutable.model;

import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.ModelBase;

import java.util.Properties;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ModelBaseState
{
    ImmReporting reporting = null;

    Properties properties = null;

    ImmList<String> modules = null;

    ImmDistributionManagement distributionManagement;

    ImmList<ImmDependency> dependencyManagement = null;

    ImmList<ImmDependency> dependencies = null;

    public ImmList<ImmRepository> repositories;

    public ImmList<ImmRepository> pluginRepositories;

    protected void setModelBaseAttributes( ModelBase modelBase){
        modelBase.setProperties( properties );
        modelBase.setDependencies( dependencies.toList( ImmDependency.mapper ) );
        if (dependencyManagement != null)
        {
            DependencyManagement dependencyManagement = new DependencyManagement();
            dependencyManagement.setDependencies( this.dependencyManagement.toList( ImmDependency.mapper ) );
            modelBase.setDependencyManagement( dependencyManagement );
        }
        modelBase.setModules( modules.toList( ImmList.stringMapper ) );
        modelBase.setDistributionManagement( distributionManagement.toDistributionManagement() );
        if (reporting != null) modelBase.setReporting( reporting.toReporting() );
    }
}
