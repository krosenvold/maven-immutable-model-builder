package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

/**
 * Created by kristian on 20.09.15.
 */
public class GroupIdBuilder
    implements Builder<GroupId>
{
    @Override
    public Builder getBuilderFor( String tagName )
    {
        throw new RuntimeException( "Unsupported child tag" + tagName );
    }

    @Override
    public GroupId from( XMLStreamReader2 node, Iterable kids, String nodeText )
    {
        return new GroupId( nodeText );
    }
}
