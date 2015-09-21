package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.stax2.XMLStreamReader2;

/**
 * Created by kristian on 20.09.15.
 */
public class VersionBuilder
    implements Builder<Version>
{
    @Override
    public Builder getBuilderFor( String tagName )
    {
        return null;
    }

    @Override
    public Version from( XMLStreamReader2 node, Iterable<ModelElement> kids, String nodeText )
    {
        return new Version( nodeText );
    }
}
