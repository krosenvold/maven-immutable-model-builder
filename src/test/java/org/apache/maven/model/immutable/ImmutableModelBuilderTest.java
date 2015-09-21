package org.apache.maven.model.immutable;

import org.apache.maven.model.immutable.model.Plugin;
import org.apache.maven.model.immutable.model.Project;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ImmutableModelBuilderTest
{

    @Test
    public void testBuildModel()
        throws Exception
    {
        InputStream resourceAsStream = this.getClass().getResourceAsStream( "/immutable/simpletest.xml" );
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        Project project = imb.buildModel( resourceAsStream );
        resourceAsStream.close();
        Plugin pluginA = project.getBuild().getPlugins().getPlugins().get( 0 );
        Plugin pluginB = project.getBuild().getPlugins().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion().getVersion() );
        assertEquals( "gid", project.getGroupId().getGroupId() );
        assertEquals( "art", project.getArtifactId().getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion().getVersion() );
    }

    @Test
    public void malformed_plugins()
        throws Exception
    {
        InputStream resourceAsStream = this.getClass().getResourceAsStream( "/immutable/simple-malformed-plugins.xml" );
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        Project project = imb.buildModel( resourceAsStream );
        resourceAsStream.close();
        Plugin pluginA = project.getBuild().getPlugins().getPlugins().get( 0 );
        Plugin pluginB = project.getBuild().getPlugins().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
        assertEquals( "4.0.0", project.getModelVersion().getVersion() );
        assertEquals( "gid", project.getGroupId().getGroupId() );
        assertEquals( "art", project.getArtifactId().getArtifactId() );
        assertEquals( "101-SNAPSHOT", project.getVersion().getVersion() );
    }

}