package org.apache.maven.model.immutable.model;

import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.ModelBase;

import java.util.List;
import java.util.Properties;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ModelBaseState
{
    ImmReporting reporting = null;

    Properties properties = null;

    List<String> modules = null;

    ImmDistributionManagement distributionManagement;

    List<ImmDependency> dependencyManagement = null;

    List<ImmDependency> dependencies = null;

    public List<ImmRepository> repositories;

    public List<ImmRepository> pluginRepositories;

    protected void setModelBaseAttributes( ModelBase modelBase){
        modelBase.setProperties( properties );
        modelBase.setDependencies( ImmDependency.asList(  dependencies ));
        if (dependencyManagement != null)
        {
            DependencyManagement dependencyManagement = new DependencyManagement();
            dependencyManagement.setDependencies( ImmDependency.asList( this.dependencyManagement ) );
            modelBase.setDependencyManagement( dependencyManagement );
        }
        modelBase.setModules( modules );
        modelBase.setDistributionManagement( distributionManagement.toDistributionManagement() );
        modelBase.setReporting( reporting.toReporting() );
    }
}
