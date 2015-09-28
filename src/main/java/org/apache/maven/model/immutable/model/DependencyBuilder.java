package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.List;

class DependencyBuilder
    implements ItemBuilder<ImmDependency>
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();

    private final LeafBuilder artifactIdBuilder = new LeafBuilder();

    private final LeafBuilder versionBuilder = new LeafBuilder();

    private final GenericListBuilder<ImmExclusion> exclusionsBuilder =
        new GenericListBuilder<>( "exclusion", new BuilderExclusion() );


    public final ImmDependency build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        String groupId = null;
        String artifactId = null;
        String version = null;
        String classifier = null;
        String scope = null;
        String systemPath = null;
        String optional = null;
        String type = null;
        List<ImmExclusion> exclusions = null;

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "groupId":
                            groupId = groupIdBuilder.build( node );
                            break;
                        case "artifactId":
                            artifactId = artifactIdBuilder.build( node );
                            break;
                        case "version":
                            version = versionBuilder.build( node );
                            break;
                        case "type":
                            type = versionBuilder.build( node );
                            break;
                        case "scope":
                            scope = versionBuilder.build( node );
                            break;
                        case "classifier":
                            classifier = versionBuilder.build( node );
                            break;
                        case "systemPath":
                            systemPath = versionBuilder.build( node );
                            break;
                        case "optional":
                            optional = versionBuilder.build( node );
                            break;
                        case "exclusions":
                            exclusions = exclusionsBuilder.build( node );
                            break;
                           default:
                              throw new RuntimeException( "Unsupported child tag" + node.getLocalName() );
                    }
            }
        }

        return new ImmDependency( groupId, artifactId, version, classifier, type, systemPath, optional, scope, exclusions );
    }
}