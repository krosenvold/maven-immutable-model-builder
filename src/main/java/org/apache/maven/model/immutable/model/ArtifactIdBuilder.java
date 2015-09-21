package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.stax2.XMLStreamReader2;

public class ArtifactIdBuilder
    implements Builder<ArtifactId>
{
    @Override
    public Builder getBuilderFor( String tagName )
    {
        throw new RuntimeException( "Unsupported model" );
    }

    @Override
    public ArtifactId from( XMLStreamReader2 node, Iterable<ModelElement> kids, String nodeText )
    {
        return new ArtifactId( nodeText );
    }

}
