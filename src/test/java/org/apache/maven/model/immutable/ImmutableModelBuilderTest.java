package org.apache.maven.model.immutable;

import org.apache.maven.model.InputSource;
import org.apache.maven.model.Model;
import org.apache.maven.model.immutable.model.Plugin;
import org.apache.maven.model.immutable.model.Project;
import org.apache.maven.model.io.xpp3.MavenXpp3ReaderEx;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ImmutableModelBuilderTest
{

    @Test
    public void testBuildModel()
        throws Exception
    {
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        Project project = imb.readProject( "/immutable/simpletest.xml" );
        Plugin pluginA = project.getBuild()
                                .getPlugins()
                                .getPlugins()
                                .get( 0 );
        Plugin pluginB = project.getBuild()
                                .getPlugins()
                                .getPlugins()
                                .get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion()
                                      .getVersion() );
        assertEquals( "gid", project.getGroupId()
                                    .getGroupId() );
        assertEquals( "art", project.getArtifactId()
                                    .getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion()
                                             .getVersion() );
    }

    @Test( expected = java.io.EOFException.class )
    public void malformed_plugins_ex()
        throws Exception
    {
        readModel( "/immutable/simple-malformed-plugins.xml" );

    }


    @Test
    @Ignore
    public void malformed_plugins()
        throws Exception
    {
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        Project project = imb.readProject( "/immutable/simple-malformed-plugins.xml" );
        Plugin pluginA = project.getBuild()
                                .getPlugins()
                                .getPlugins()
                                .get( 0 );
        Plugin pluginB = project.getBuild()
                                .getPlugins()
                                .getPlugins()
                                .get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion()
                                      .getVersion() );
        assertEquals( "gid", project.getGroupId()
                                    .getGroupId() );
        assertEquals( "art", project.getArtifactId()
                                    .getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion()
                                             .getVersion() );
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
        Project project = imb.readProject( "/immutable/plugin-configuration-parent.xml" );
        assertNotNull( project );
    }

    @Test
    public void mavencorePom()
        throws IOException, XMLStreamException
    {
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        Project project = imb.readProject( "/immutable/maven-core.xml" );
        assertNotNull( project );
    }

    private void xpp3read()
        throws IOException, XmlPullParserException
    {
        long start;
        long hc;
        start = System.currentTimeMillis();
        hc = 0;
        for ( int i = 0; i < 1000; i++ )
        {
            Model model = readModel( "/immutable/simpletest.xml" );
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
            Project project = imb.readProject( "/immutable/simpletest.xml" );
            hc += project.hashCode();
        }
        System.out.println( "stax = " + ( System.currentTimeMillis() - start ) + "hc = " + hc );
    }


    private Model readModel( String name )
        throws IOException, XmlPullParserException
    {
        MavenXpp3ReaderEx org = new MavenXpp3ReaderEx();
        try ( InputStream resourceAsStream = this.getClass().getResourceAsStream( name ) )
        {
            return org.read( resourceAsStream, true, new InputSource() );
        }
    }


}