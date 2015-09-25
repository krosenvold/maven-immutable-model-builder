package org.apache.maven.model.immutable.model;

public class ImmModelVersion
{
    private final String version;

    public ImmModelVersion( String version )
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }

}
