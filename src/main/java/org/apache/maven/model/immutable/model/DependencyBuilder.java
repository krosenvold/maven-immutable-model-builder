package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

/**
 * Created by kristian on 24.09.15.
 */
class DependencyBuilder
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
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "groupId":
                            groupId = new ImmGroupId( groupIdBuilder.singleTextValue( node ) );
                            break;
                        case "artifactId":
                            artifactId = new ImmArtifactId( artifactIdBuilder.singleTextValue( node ) );
                            break;
                        case "version":
                            version = new ImmVersion( versionBuilder.singleTextValue( node ) );
                            break;
                        case "type":
                            type = versionBuilder.singleTextValue( node );
                            break;
                        case "scope":
                            scope = versionBuilder.singleTextValue( node );
                            break;
                        case "classifier":
                            classifier = versionBuilder.singleTextValue( node );
                            break;
                        case "systemPath":
                            systemPath = versionBuilder.singleTextValue( node );
                            break;
                        case "optional":
                            optional = versionBuilder.singleTextValue( node );
                            break;
                    }
            }
        }

        return new ImmDependency( groupId, artifactId, version, classifier, type, systemPath, optional, scope );
    }
}