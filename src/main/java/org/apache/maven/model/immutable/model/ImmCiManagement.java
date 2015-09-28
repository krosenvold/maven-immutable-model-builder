package org.apache.maven.model.immutable.model;

import org.apache.maven.model.CiManagement;
import org.apache.maven.model.Notifier;

import java.util.List;

public class ImmCiManagement
{
    private final String system;

    private final String url;

    private final List<ImmNotifier> notifiers;

    public ImmCiManagement( String system, String url, List<ImmNotifier> notifiers )
    {

        this.system = system;
        this.url = url;
        this.notifiers = notifiers;
    }

    public CiManagement toCiManagement(){
        CiManagement ciManagement = new CiManagement();
        ciManagement.setUrl( url );
        ciManagement.setSystem( system );

        //ciManagement.setNotifiers(  ); TODO fix
      //  ciManagement.setNotifiers(  );
        return ciManagement;
    }
}
