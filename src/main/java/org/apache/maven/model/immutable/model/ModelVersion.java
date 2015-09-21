package org.apache.maven.model.immutable.model;

public class ModelVersion
{
    private final String version;

    public ModelVersion( String version )
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }

}
