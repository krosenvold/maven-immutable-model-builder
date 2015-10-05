package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

class ResourceBuilder
    implements ItemBuilder<ImmResource>
{
    private final GavFieldBuilder gavFieldBuilder = new GavFieldBuilder();

    private final LeafBuilder leafBuilder = new LeafBuilder();

    private final GenericListBuilder<String> includesBuilder =
        new GenericListBuilder( "include", leafBuilder);

    private final GenericListBuilder<String> excludesBuilder =
        new GenericListBuilder( "exclude", leafBuilder);


    XmlBuilder xmlBuilder = new XmlBuilder();


    public final ImmResource build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String directory = null;
        StringBuilder reportSets = null;

        GavState gavState = new GavState();
        String targetPath = null;
        String filtering = null;
        List<String> includes = null;
        List<String> excludes = null;
        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    if ( !gavFieldBuilder.build( node, gavState ) )
                    {
                        switch ( node.getLocalName() )
                        {
                            case "directory":
                                directory = leafBuilder.build( node );
                                break;
                            case "targetPath":
                                targetPath = leafBuilder.build( node );
                                break;
                            case "filtering":
                                filtering = leafBuilder.build( node );
                                break;
                            case "includes":
                                includes = includesBuilder.build( node );
                                break;
                            case "excludes":
                                excludes = includesBuilder.build( node );
                                break;
                        }
                    }
            }
        }

        return new ImmResource( directory, targetPath, filtering, includes, excludes );
    }
}
