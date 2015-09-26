package org.apache.maven.model.immutable;

import org.apache.maven.model.Activation;
import org.apache.maven.model.ActivationProperty;
import org.apache.maven.model.Contributor;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DistributionManagement;
import org.apache.maven.model.InputSource;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.Profile;
import org.apache.maven.model.immutable.model.ImmProject;
import org.apache.maven.model.io.xpp3.MavenXpp3ReaderEx;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Xpp3StaxComparisonTest
{

    private final URL simpleTest = this.getClass()
                                       .getResource( "/immutable/simpletest.xml" );

    private final URL m3core = this.getClass().getResource( "/immutable/maven-core.xml" );

    private final int NUM_READS = 1000;


    @Test
    public void simpleBM2()
        throws IOException, XMLStreamException, XmlPullParserException
    {
        for ( int i = 0; i < 10; i++ )
        {
            xpp3read();
        }
        System.gc();
        for ( int i = 0; i < 10; i++ )
        {
            staxRead();
        }
    }

    @Test
    public void mavencorePom()
        throws IOException, XMLStreamException
    {
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        ImmProject project = imb.readProject( "/immutable/maven-core.xml" );
        assertNotNull( project );
    }

    @Test
    public void assertCorePom()
        throws IOException, XmlPullParserException
    {

        Model model = readModelXpp3( "/immutable/maven-core.xml" );
        assertCoreModel( model );
    }

    private void assertCoreModel( Model model )
    {
        assertEquals( "maven", model.getArtifactId());
        assertEquals( "zz", model.getGroupId());
        assertEquals( "3.3.7-SNAPSHOT", model.getVersion());
        assertEquals( "pom", model.getPackaging());
        assertEquals("Apache Maven Not", model.getName());
        assertEquals("DescrA", model.getDescription());
        assertEquals("http://maven.apache.org/ref/${project.version}/", model.getUrl());
        assertEquals("2001", model.getInceptionYear());
        assertParent( model.getParent() );
        assertProperties( model );
        assertModules( model );
        assertScm( model );
        assertIssueManagement( model );
        assertCiManagement( model);
        assertDistributionManagement( model);
        assertContributors( model );
        assertEquals("2.2.1", model.getPrerequisites().getMaven());
        assertDependecies( model );
        assertDependencyManagement( model );
        assertDependencyManagement( model );
        assertPluginManagement(model);
        assertProfiles( model);

    }

    private void assertProfiles( Model model )
    {
        final List<Profile> profiles = model.getProfiles();
        assertEquals( 3, profiles.size());
        final Profile first = profiles.get( 0 );
        assertEquals( "apache-release",first.getId());
        final Activation activation = first.getActivation();
        assertEquals( "1.9", activation.getJdk() );
        assertEquals( "foo.txt", activation.getFile().getExists() );
        assertEquals( "bar.txt", activation.getFile().getMissing() );
        assertEquals( "i386", activation.getOs().getArch());
        assertEquals( "raspian", activation.getOs().getName());
        assertEquals( "debian", activation.getOs().getFamily());
        assertEquals( "1.0", activation.getOs().getVersion());
        assertEquals( false, activation.isActiveByDefault() );
        final ActivationProperty property = activation.getProperty();
        assertEquals( "maven.repo.local", property.getName() );

        final org.apache.maven.model.Plugin plugin = first.getBuild()
                                                          .getPlugins()
                                                          .get( 0 );
        assertEquals("maven-assembly-plugin", plugin.getArtifactId());
        assertEquals("org.apache.maven.plugins", plugin.getGroupId()); // Default value

        final Profile second = profiles.get( 1 );
        assertEquals(4, second.getReporting().getPlugins().size());



    }

    private void assertPluginManagement( Model model )
    {
        final PluginManagement pluginManagement = model.getBuild()
                                                       .getPluginManagement();
        assertEquals( 12, pluginManagement.getPlugins().size());
        final org.apache.maven.model.Plugin first = pluginManagement.getPlugins()
                                                                     .get( 0 );
        assertEquals( "org.codehaus.plexus", first.getGroupId() );
        assertEquals( "plexus-component-metadata", first.getArtifactId() );
        assertEquals( "${plexusVersion}", first.getVersion() );
        List<PluginExecution> executions = first.getExecutions();
        assertEquals( 1, executions.size() );
        assertEquals( "generate-metadata", executions.get( 0 ).getGoals().get(0) );
        assertEquals( "generate-test-metadata", executions.get( 0 ).getGoals().get(1) );
        // todo: fill in rest of fields here
        final org.apache.maven.model.Plugin fifth = pluginManagement.getPlugins()
                                                                    .get( 4 );
        assertEquals( "org.codehaus.modello", fifth.getGroupId() );
        assertEquals( "modello-maven-plugin", fifth.getArtifactId() );
        assertEquals( "${modelloVersion}", fifth.getVersion() );
        executions = fifth.getExecutions();
        assertEquals( 2, executions.size() );
        final PluginExecution firstexec = executions.get( 0 );
        assertEquals( "site-docs", firstexec.getId() );
        assertEquals( "pre-site", firstexec.getPhase() );

        assertEquals( "xdoc", firstexec.getGoals() .get( 0 ) );
        assertEquals( "xsd", firstexec.getGoals().get(1) );
        final Xpp3Dom configuration = (Xpp3Dom) fifth.getConfiguration();
        assertEquals( "foo", configuration.getChild( "lifecycleMappingMetadata" ).getValue());


    }

    private void assertDependencyManagement( Model model )
    {
        final List<Dependency> dependencies = model.getDependencyManagement()
                                                   .getDependencies();
        assertEquals( 36, dependencies.size() );
        final Dependency d1 = dependencies.get( 0 );
        assertEquals( "org.apache.maven", d1.getGroupId() );
        assertEquals( "maven-model", d1.getArtifactId() );
        assertEquals( "${project.version}", d1.getVersion() );
        assertEquals( "foo", d1.getExclusions().get(0).getGroupId() );
        assertEquals( "bar", d1.getExclusions().get(0).getArtifactId() );
        assertEquals( "fud", d1.getClassifier() );
        assertEquals( "jar", d1.getType() );
        assertEquals( "abc", d1.getSystemPath() );
        assertEquals( "true", d1.getOptional() );
    }

    private void assertDependecies( Model model )
    {
        final List<Dependency> dependencies = model.getDependencies();
        assertEquals( 1, dependencies.size() );
        final Dependency d1 = dependencies.get( 0 );
        assertEquals( "junit.org", d1.getGroupId() );
        assertEquals( "junit", d1.getArtifactId() );
        assertEquals( "${junitVersion}", d1.getVersion() );
        assertEquals( "test", d1.getScope() );
    }
    private void assertContributors( Model model )
    {
        assertEquals(7, model.getContributors().size());
        final Contributor c1 = model.getContributors().get( 0 );
        assertEquals( "Stuart McCulloch", c1.getName() );
        assertEquals( "stuart@test.com", c1.getEmail());
        assertEquals( "anOrg", c1.getOrganization());
        assertEquals( "http://anOrg.org", c1.getOrganizationUrl());
        assertEquals( "-7", c1.getTimezone());
        assertEquals( "http://me", c1.getUrl());
        assertEquals("Phil Pratt-Szeliga (MNG-5645)", model.getContributors().get(6).getName());
    }

    private void assertIssueManagement( Model model )
    {
        assertEquals("jira", model.getIssueManagement().getSystem());
        assertEquals("https://issues.apache.org/jira/browse/MNG", model.getIssueManagement().getUrl());
    }

    private void assertDistributionManagement( Model model )
    {
        final DistributionManagement distMgmt = model.getDistributionManagement();
        assertEquals( "http://maven.apache.org/download.html", distMgmt
                                                                    .getDownloadUrl());
        assertEquals( "apache.website", distMgmt
                                             .getSite().getId());
        assertEquals( "scm:svn:https://svn.apache.org/repos/infra/websites/production/maven/components/${maven.site.path}", distMgmt
                                                                                                                                 .getSite().getUrl());
    }

    private void assertCiManagement( Model model )
    {
        assertEquals("Jenkins", model.getCiManagement().getSystem());
        assertEquals("https://builds.apache.org/job/maven-3.x/", model.getCiManagement().getUrl());
    }

    private void assertScm( Model model )
    {
        assertEquals( "scm:git:https://git-wip-us.apache.org/repos/asf/maven.git", model.getScm().getConnection());
        assertEquals( "scm:git!:https://git-wip-us.apache.org/repos/asf/maven.git", model.getScm().getDeveloperConnection());
        assertEquals( "https://github.com/apache/maven/tree/${project.scm.tag}", model.getScm().getUrl());
        assertEquals( "master", model.getScm().getTag());
    }

    private void assertModules( Model model )
    {
        assertEquals( 13, model.getModules().size());
        assertEquals( "maven-plugin-api", model.getModules().get(0));
        assertEquals( "apache-maven", model.getModules().get(12));
    }

    private void assertProperties( Model model )
    {
        final Properties properties = model.getProperties();
        assertEquals( 25, properties.size());
        assertEquals( "1.8", properties.get("maven.compiler.source")); // wishful thinking
        assertEquals( "2.5.2", properties.get("classWorldsVersion"));
        assertEquals( "**/package-info.java", properties.get("checkstyle.excludes"));
    }

    private void assertParent( Parent parent )
    {
        assertEquals( "org.apache.maven", parent.getGroupId() );
        assertEquals( "maven-parent", parent.getArtifactId() );
        assertEquals( "26", parent.getVersion() );
        assertEquals( "../pom/maven/pom.xml", parent.getRelativePath() );
    }

    // TOOD: Test Case sensitvity of tagnames ?
    // TODO: Test more malformed poms

    private void xpp3read()
        throws IOException, XmlPullParserException
    {
        long start;
        long hc;
        start = System.currentTimeMillis();
        hc = 0;
        for ( int i = 0; i < NUM_READS; i++ )
        {
            Model model = readModelXpp3( m3core );
            hc += model.hashCode();
        }
        System.out.println( "xpp3 = " + ( System.currentTimeMillis() - start ) + "hc = " + hc );
    }

    ImmutableModelBuilder imb = new ImmutableModelBuilder();

    private void staxRead()
        throws XMLStreamException, IOException
    {
        long start = System.currentTimeMillis();
        long hc = 0;
        for ( int i = 0; i < NUM_READS; i++ )
        {
            ImmProject project = imb.readProject( m3core );
            hc += project.hashCode();
        }
        System.out.println( "stax = " + ( System.currentTimeMillis() - start ) + "hc = " + hc );
    }


    private Model readModelXpp3( String name )
        throws IOException, XmlPullParserException
    {
        MavenXpp3ReaderEx org = new MavenXpp3ReaderEx();
        try (InputStream resourceAsStream = this.getClass()
                                                .getResourceAsStream( name ))
        {
            return org.read( resourceAsStream, true, new InputSource() );
        }
    }

    private Model readModelXpp3( URL name )
        throws IOException, XmlPullParserException
    {
        MavenXpp3ReaderEx org = new MavenXpp3ReaderEx();
        try (InputStream resourceAsStream = name.openStream())
        {
            return org.read( resourceAsStream, true, new InputSource() );
        }
    }

}