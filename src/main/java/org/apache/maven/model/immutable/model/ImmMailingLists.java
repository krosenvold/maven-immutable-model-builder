package org.apache.maven.model.immutable.model;

import java.util.List;

/**
 * Created by kristian on 24.09.15.
 */
public class ImmMailingLists
{
    List<ImmMailingList> lists;

    public ImmMailingLists( List<ImmMailingList> lists )
    {
        this.lists = lists;
    }

    public List<ImmMailingList> getLists()
    {
        return lists;
    }
}
