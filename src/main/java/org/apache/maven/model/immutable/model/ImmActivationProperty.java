package org.apache.maven.model.immutable.model;

import org.apache.maven.model.ActivationProperty;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class ImmActivationProperty
{
    private final String name;

    private final String value;

    public ImmActivationProperty( String name, String value )
    {

        this.name = name;
        this.value = value;
    }

    public ActivationProperty toActivationProperty()
    {
        ActivationProperty activationProperty = new ActivationProperty();
        activationProperty.setName( name );
        activationProperty.setValue( value );
        return activationProperty;
    }

}
