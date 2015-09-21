package org.apache.maven.model.immutable;

import org.apache.maven.model.immutable.model.Build;
import org.apache.maven.model.immutable.model.Plugin;
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
        Build modelElement = imb.buildModel( resourceAsStream );
        resourceAsStream.close();
        Plugin pluginA = modelElement.getPlugins().getPlugins().get( 0 );
        Plugin pluginB = modelElement.getPlugins().getPlugins().get( 1 );
        assertEquals( "A-G:A:1.0", pluginA.toString() );
        assertEquals( "B-G:B:2.0", pluginB.toString() );
    }
}