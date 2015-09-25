package org.apache.maven.model.immutable.model;

import org.apache.maven.model.immutable.ModelElement;

public class ImmVersion
    extends ModelElement
{
    private final String version;

    public ImmVersion( String version )
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }
}
