package org.apache.maven.model.immutable.model;

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

    List<ImmProfile> profiles = null;

    public List<ImmRepository> repositories;

    public List<ImmRepository> pluginRepositories;
}
