package org.apache.maven.model.immutable;

import java.util.Arrays;

/**
 * Created by kristian on 20.09.15.
 */
public class Node
{

    private final String nodeName;

    private final Node[] children;

    public Node( String nodeName, Node[] children )
    {
        this.nodeName = nodeName;
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "Node{" +
            "nodeName='" + nodeName + '\'' +
            ", children=" + Arrays.toString( children ) +
            '}';
    }
}
