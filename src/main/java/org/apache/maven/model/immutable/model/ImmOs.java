package org.apache.maven.model.immutable.model;

import org.apache.maven.model.ActivationOS;

public class ImmOs
{
    private final String family;

    private final String name;

    private final String arch;

    private final String version;

    public ImmOs( String family, String name, String arch, String version )
    {

        this.family = family;
        this.name = name;
        this.arch = arch;
        this.version = version;
    }

    public ActivationOS toActivationOS()
    {
        ActivationOS result = new ActivationOS();
        result.setName( name );
        result.setArch( arch );
        result.setVersion( version );
        result.setFamily( family );
        return result;
    }
}
