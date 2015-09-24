package org.apache.maven.model.immutable.model;

import java.util.List;

/**
 * Created by kristian on 24.09.15.
 */
public class MailingLists
{
    List<MailingList> lists;

    public MailingLists( List<MailingList> lists )
    {
        this.lists = lists;
    }

    public List<MailingList> getLists()
    {
        return lists;
    }
}
