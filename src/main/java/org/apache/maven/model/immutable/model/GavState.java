package org.apache.maven.model.immutable.model;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class GavState
{
    String groupId;

    String artifactId;

    String version;

    public Gav gav()
    {
        return new Gav( groupId, artifactId, version );
    }


}
