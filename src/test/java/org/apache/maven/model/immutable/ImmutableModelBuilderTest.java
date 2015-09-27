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
        ImmProject project = imb.readProject( simpleTest );
        ImmPlugin pluginA = project.getBuild().getPlugins().get( 0 );
        ImmPlugin pluginB = project.getBuild().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion().getVersion() );
        assertEquals( "gid", project.getGroupId() );
        assertEquals( "art", project.getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion() );
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
        ImmPlugin pluginA = project.getBuild().getPlugins().get( 0 );
        ImmPlugin pluginB = project.getBuild().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion().getVersion() );
        assertEquals( "gid", project.getGroupId() );
        assertEquals( "art", project.getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion() );
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


    private Model readModelXpp3( String name )
        throws IOException, XmlPullParserException
    {
        MavenXpp3ReaderEx org = new MavenXpp3ReaderEx();
        try ( InputStream resourceAsStream = this.getClass().getResourceAsStream( name ) )
        {
            return org.read( resourceAsStream, true, new InputSource() );
        }
    }

}