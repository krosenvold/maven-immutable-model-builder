package org.apache.maven.model.immutable;

import org.apache.maven.model.InputSource;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.immutable.model.ImmPlugin2;
import org.apache.maven.model.immutable.model.ImmProject;
import org.apache.maven.model.io.xpp3.MavenXpp3ReaderEx;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImmutableModelBuilderTest
{

    private final URL simpleTest = this.getClass().getResource( "/immutable/simpletest.xml" );

    @Test
    public void testBuildModel()
        throws Exception
    {
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        ImmProject project = imb.readProject( "/immutable/simpletest.xml" );
        ImmPlugin2 pluginA = project.getBuild().getPlugins().getPlugins().get( 0 );
        ImmPlugin2 pluginB = project.getBuild().getPlugins().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion().getVersion() );
        assertEquals( "gid", project.getGroupId().getGroupId() );
        assertEquals( "art", project.getArtifactId().getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion().getVersion() );
    }

    @Test( expected = java.io.EOFException.class )
    public void malformed_plugins_ex()
        throws Exception
    {
        readModelXpp3( "/immutable/simple-malformed-plugins.xml" );

    }


    @Test
    @Ignore
    public void malformed_plugins()
        throws Exception
    {
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        ImmProject project = imb.readProject( "/immutable/simple-malformed-plugins.xml" );
        ImmPlugin2 pluginA = project.getBuild().getPlugins().getPlugins().get( 0 );
        ImmPlugin2 pluginB = project.getBuild().getPlugins().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion().getVersion() );
        assertEquals( "gid", project.getGroupId().getGroupId() );
        assertEquals( "art", project.getArtifactId().getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion().getVersion() );
    }

    @Test
    public void simpleBM()
        throws IOException, XMLStreamException, XmlPullParserException
    {
        staxRead();
        xpp3read();
        staxRead();
        xpp3read();
        staxRead();
        xpp3read();
        staxRead();
        xpp3read();
    }

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
    public void moreCOmplex()
        throws IOException, XMLStreamException
    {
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        ImmProject project = imb.readProject( "/immutable/plugin-configuration-parent.xml" );
        assertNotNull( project );
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
        assertEquals( "maven", model.getArtifactId() );
        assertEquals( "zz", model.getGroupId() );
        assertEquals( "3.3.7-SNAPSHOT", model.getVersion() );
        assertEquals( "pom", model.getPackaging() );
        assertEquals( "Apache Maven Not", model.getName() );
        assertEquals( "DescrA", model.getDescription() );
        assertEquals( "http://maven.apache.org/ref/${project.version}/", model.getUrl() );
        assertEquals( "2001", model.getInceptionYear() );
        assertParent( model.getParent() );
        assertProperties( model );
        assertModules( model );
        assertScm( model );
        assertIssueManagement( model );
        assertCiManagement( model );
    }

    private void assertIssueManagement( Model model )
    {
        assertEquals( "jira", model.getIssueManagement().getSystem() );
        assertEquals( "https://issues.apache.org/jira/browse/MNG", model.getIssueManagement().getUrl() );
    }

    private void assertDistributionManagement( Model model )
    {
        assertEquals( "http://maven.apache.org/download.html", model.getDistributionManagement().getDownloadUrl() );
        assertEquals( "https://issues.apache.org/jira/browse/MNG", model.getIssueManagement().getUrl() );
    }

    private void assertCiManagement( Model model )
    {
        assertEquals( "Jenkins", model.getCiManagement().getSystem() );
        assertEquals( "https://builds.apache.org/job/maven-3.x/", model.getCiManagement().getUrl() );
    }

    private void assertScm( Model model )
    {
        assertEquals( "scm:git:https://git-wip-us.apache.org/repos/asf/maven.git", model.getScm().getConnection() );
        assertEquals( "scm:git!:https://git-wip-us.apache.org/repos/asf/maven.git",
                      model.getScm().getDeveloperConnection() );
        assertEquals( "https://github.com/apache/maven/tree/${project.scm.tag}", model.getScm().getUrl() );
        assertEquals( "master", model.getScm().getTag() );
    }

    private void assertModules( Model model )
    {
        assertEquals( 13, model.getModules().size() );
        assertEquals( "maven-plugin-api", model.getModules().get( 0 ) );
        assertEquals( "apache-maven", model.getModules().get( 12 ) );
    }

    private void assertProperties( Model model )
    {
        final Properties properties = model.getProperties();
        assertEquals( 25, properties.size() );
        assertEquals( "1.8", properties.get( "maven.compiler.source" ) ); // wishful thinking
        assertEquals( "2.5.2", properties.get( "classWorldsVersion" ) );
        assertEquals( "**/package-info.java", properties.get( "checkstyle.excludes" ) );
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
        for ( int i = 0; i < 1000; i++ )
        {
            Model model = readModelXpp3( simpleTest );
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
        for ( int i = 0; i < 1000; i++ )
        {
            ImmProject project = imb.readProject( simpleTest );
            hc += project.hashCode();
        }
        System.out.println( "stax = " + ( System.currentTimeMillis() - start ) + "hc = " + hc );
    }


    private Model readModelXpp3( String name )
        throws IOException, XmlPullParserException
    {
        MavenXpp3ReaderEx org = new MavenXpp3ReaderEx();
        try ( InputStream resourceAsStream = this.getClass().getResourceAsStream( name ) )
        {
            return org.read( resourceAsStream, true, new InputSource() );
        }
    }

    private Model readModelXpp3( URL name )
        throws IOException, XmlPullParserException
    {
        MavenXpp3ReaderEx org = new MavenXpp3ReaderEx();
        try ( InputStream resourceAsStream = name.openStream() )
        {
            return org.read( resourceAsStream, true, new InputSource() );
        }
    }

}