package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

public class Version
    extends ModelElement
{
    private final String version;

    public Version( String version )
    {

        this.version = version;
    }
}
