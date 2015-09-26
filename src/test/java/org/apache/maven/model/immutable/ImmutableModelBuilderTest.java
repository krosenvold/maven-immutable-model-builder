package org.apache.maven.model.immutable;

import org.apache.maven.model.InputSource;
import org.apache.maven.model.Model;
import org.apache.maven.model.immutable.model.ImmPlugin;
import org.apache.maven.model.immutable.model.ImmProject;
import org.apache.maven.model.io.xpp3.MavenXpp3ReaderEx;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
        ImmPlugin pluginA = project.getBuild().getPlugins().getPlugins().get( 0 );
        ImmPlugin pluginB = project.getBuild().getPlugins().getPlugins().get( 1 );
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
        ImmPlugin pluginA = project.getBuild().getPlugins().getPlugins().get( 0 );
        ImmPlugin pluginB = project.getBuild().getPlugins().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion().getVersion() );
        assertEquals( "gid", project.getGroupId().getGroupId() );
        assertEquals( "art", project.getArtifactId().getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion().getVersion() );
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