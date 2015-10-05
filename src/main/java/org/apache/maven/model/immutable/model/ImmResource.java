package org.apache.maven.model.immutable.model;

import org.apache.maven.model.Extension;
import org.apache.maven.model.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ImmResource
{
    private List<String> includes;
    private List<String> excludes;
    private final String directory;
    private final String targetPath;
    private final String filtering;

    static final Function<ImmResource, Resource> mapper = new Function<ImmResource, Resource>()
    {
        @Override
        public Resource apply( ImmResource immExtension )
        {
            return immExtension.toResource();
        }
    };

    public ImmResource( String directory, String targetPath, String filtering, List<String> includes,
                        List<String> excludes )
    {
        this.directory = directory;
        this.targetPath = targetPath;
        this.filtering = filtering;
        this.includes = includes;
        this.excludes = excludes;
    }

    public Resource toResource(){
        Resource resource = new Resource();
        resource.setDirectory(  directory );
        resource.setFiltering(  Boolean.valueOf( filtering ));
        resource.setTargetPath( targetPath );
        resource.setIncludes( includes );
        resource.setExcludes(  excludes );
        // todo: Investigate mergedId field of Resource.
        return resource;
    }

    public static List<Resource> asResource(Iterable<ImmResource> re){
        List<Resource> result = new ArrayList<>(  );
        for ( ImmResource immResource : re )
        {
            result.add( immResource.toResource());
        }
        return result;
    }
}
