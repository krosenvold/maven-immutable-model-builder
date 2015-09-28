package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Notifier;

import java.util.Properties;

public class ImmNotifier
{
    private final String sendOnError;

    private final String sendOnFailure;

    private final String sendOnSuccess;

    private final String sendOnWarning;

    private final Properties configuration;

    private final String type;

    private final String address;

    public ImmNotifier( String sendOnError, String sendOnFailure, String sendOnSuccess, String sendOnWarning,
                        Properties configuration, String type, String address )
    {

        this.sendOnError = sendOnError;
        this.sendOnFailure = sendOnFailure;
        this.sendOnSuccess = sendOnSuccess;
        this.sendOnWarning = sendOnWarning;
        this.configuration = configuration;
        this.type = type;
        this.address = address;
    }

    public Notifier toNotifier(){
        Notifier notifier = new Notifier();
        notifier.setAddress( address );
        if (sendOnError != null) notifier.setSendOnError( Boolean.valueOf( sendOnError ));
        if (sendOnFailure != null) notifier.setSendOnFailure( Boolean.valueOf( sendOnFailure ));
        if (sendOnSuccess != null) notifier.setSendOnSuccess( Boolean.valueOf( sendOnSuccess ));
        if (sendOnWarning != null) notifier.setSendOnWarning( Boolean.valueOf( sendOnWarning ));
        notifier.setType(  type );
        notifier.setConfiguration(configuration     );
        return notifier;
    }
}
