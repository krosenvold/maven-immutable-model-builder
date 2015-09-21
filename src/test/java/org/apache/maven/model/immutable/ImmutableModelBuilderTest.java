package org.apache.maven.model.immutable;

import org.junit.Test;

import java.io.InputStream;

public class ImmutableModelBuilderTest
{

    @Test
    public void testBuildModel()
        throws Exception
    {
        InputStream resourceAsStream = this.getClass().getResourceAsStream( "/poms/inheritance/simpletest.xml" );
        ImmutableModelBuilder imb = new ImmutableModelBuilder();
        ModelElement modelElement = imb.buildModel( resourceAsStream );

        resourceAsStream.close();
    }
}