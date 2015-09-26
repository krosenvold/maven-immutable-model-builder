package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class DependencyBuilder
    implements ItemBuilder<ImmDependency>
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();

    private final LeafBuilder artifactIdBuilder = new LeafBuilder();

    private final LeafBuilder versionBuilder = new LeafBuilder();


    public ImmDependency build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        ImmGroupId groupId = null;
        ImmArtifactId artifactId = null;
        ImmVersion version = null;
        String classifier = null;
        String scope = null;
        String systemPath = null;
        String optional = null;
        String type = null;
        /*
    private List<Exclusion> exclusions;
    private Map<Object, InputLocation> locations;
         */

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            switch ( node.next() )
            {
                case XMLStreamReader2.START_ELEMENT:
                    switch ( node.getLocalName() )
                    {
                        case "groupId":
                            groupId = new ImmGroupId( groupIdBuilder.build( node ) );
                            break;
                        case "artifactId":
                            artifactId = new ImmArtifactId( artifactIdBuilder.build( node ) );
                            break;
                        case "version":
                            version = new ImmVersion( versionBuilder.build( node ) );
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
                            // todo: fix
                            break;
                        //   default:
                        //      throw new RuntimeException( "Unsupported child tag" + node.getLocalName() );
                    }
            }
        }

        return new ImmDependency( groupId, artifactId, version, classifier, type, systemPath, optional, scope );
    }
}