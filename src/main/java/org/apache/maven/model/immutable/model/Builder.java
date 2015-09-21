package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;
import org.codehaus.stax2.XMLStreamReader2;

/**
 * Created by kristian on 20.09.15.
 */
public interface Builder<T extends ModelElement>
{
    public Builder getBuilderFor( String tagName );

    public T from( XMLStreamReader2 node, Iterable<ModelElement> kids, String nodeText );
}
