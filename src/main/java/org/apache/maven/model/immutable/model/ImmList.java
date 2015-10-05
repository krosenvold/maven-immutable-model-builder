package org.apache.maven.model.immutable.model;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ImmList<T>
{
    static final Function<String, String> stringMapper = new Function<String, String>()
    {
        @Override
        public String apply( String immExtension )
        {
            return immExtension;
        }
    };


    private final T[] items;

    public ImmList( T[] items )
    {
        this.items = items;
    }

    public T get(int index){
        return items[index];
    }
    public <Z> List<Z> toList( Function<T, Z> items )
    {
        List<Z> result = new ArrayList<>( this.items.length );
        for ( T item : this.items )
        {
            result.add( items.apply( item ) );
        }
        return result;
    }

    public <Z> void setList( Function<T, Z> items, Consumer<List<Z>> consumer )
    {
        final List<Z> zs = toList( items );
        consumer.accept( zs );
    }

    public static <T> ImmList<T> of( List<T> build )
    {
        return new ImmList<T>( (T[]) build.toArray( new Object[build.size()] ) );
    }
}
