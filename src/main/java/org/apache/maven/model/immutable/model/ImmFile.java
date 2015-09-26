package org.apache.maven.model.immutable.model;

import org.apache.maven.model.ActivationFile;

public class ImmFile
{
    private final String exists;

    private final String missing;

    public ImmFile( String exists, String missing )
    {

        this.exists = exists;
        this.missing = missing;
    }

    public ActivationFile toFile()
    {
        ActivationFile result = new ActivationFile();
        result.setExists( exists );
        result.setMissing( missing );
        return result;
    }

}
