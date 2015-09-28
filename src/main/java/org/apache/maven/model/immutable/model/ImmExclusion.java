package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Exclusion;

import java.beans.ExceptionListener;
import java.util.ArrayList;
import java.util.List;

public class ImmExclusion
{
    private final String groupId;

    private final String artifactId;

    public ImmExclusion( String groupId, String artifactId )
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    public Exclusion toExclusion(){
        Exclusion result = new Exclusion();
        result.setGroupId( groupId );
        result.setArtifactId( artifactId );
        return result;
    }

    public static List<Exclusion> toList(Iterable<ImmExclusion> src){
        List<Exclusion> result = new ArrayList<>(  );
        for ( ImmExclusion immExclusion : src )
        {
            result.add( immExclusion.toExclusion());
        }
        return result;
    }
}
