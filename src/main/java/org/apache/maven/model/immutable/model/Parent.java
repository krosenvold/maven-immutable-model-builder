package org.apache.maven.model.immutable.model;

public class Parent
{

    final Gav gav;

    final String relativePath;

    public Parent( String relativePath, Gav gav )
    {
        this.relativePath = relativePath;
        this.gav = gav;
    }
}
