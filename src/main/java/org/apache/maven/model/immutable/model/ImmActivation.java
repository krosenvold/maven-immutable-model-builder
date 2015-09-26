package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Activation;

public class ImmActivation
{
    private final ImmActivationProperty properties;

    private final String jdk;

    private final ImmFile file;

    private final String activeByDefault;

    private final ImmOs os;

    public ImmActivation( ImmActivationProperty properties, String jdk, ImmFile file, String activeByDefault, ImmOs os )
    {

        this.properties = properties;
        this.jdk = jdk;
        this.file = file;
        this.activeByDefault = activeByDefault;
        this.os = os;
    }

    public Activation toActivation()
    {
        Activation result = new Activation();
        result.setActiveByDefault( Boolean.valueOf( activeByDefault ) );
        if ( file != null )
        {
            result.setFile( file.toFile() );
        }
        result.setJdk( jdk );
        result.setOs( os.toActivationOS() );
        if ( properties != null )
        {
            result.setProperty( properties.toActivationProperty() );
        }
        return result;
    }
}
