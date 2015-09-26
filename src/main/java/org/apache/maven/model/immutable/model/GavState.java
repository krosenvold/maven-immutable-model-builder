package org.apache.maven.model.immutable.model;

/**
 * @author Kristian Rosenvold krosenvold@apache.org
 */
public class GavState
{
    ImmGroupId groupId;

    ImmArtifactId artifactId;

    ImmVersion version;

    public Gav gav()
    {
        return new Gav( groupId, artifactId, version );
    }


}
