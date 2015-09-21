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
        InputStream resourceAsStream = this.getClass().getResourceAsStream( "/poms/inheritance/simpletest.xml" );
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        Project project = imb.buildModel( resourceAsStream );
        resourceAsStream.close();
        Plugin pluginA = project.getBuild().getPlugins().getPlugins().get( 0 );
        Plugin pluginB = project.getBuild().getPlugins().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
    }
}