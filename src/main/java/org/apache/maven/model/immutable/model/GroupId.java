package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

/**
 * Created by kristian on 20.09.15.
 */
public class GroupId
    extends ModelElement
{

    private final String groupId;

    public GroupId( String groupId )
    {
        this.groupId = groupId;
    }
}
