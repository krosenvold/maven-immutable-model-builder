package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ImmGroupId
    extends ModelElement
{

    private final String groupId;

    public ImmGroupId( String groupId )
    {
        this.groupId = groupId;
    }

    public String getGroupId()
    {
        return groupId;
    }
}
