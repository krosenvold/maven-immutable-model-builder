package org.apache.maven.model.immutable.model;

public class ImmParent
{

    final Gav gav;

    final String relativePath;

    public ImmParent( String relativePath, Gav gav )
    {
        this.relativePath = relativePath;
        this.gav = gav;
    }
}
