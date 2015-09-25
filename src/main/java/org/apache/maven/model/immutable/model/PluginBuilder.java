package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;

class PluginBuilder
{
    private final LeafBuilder groupIdBuilder = new LeafBuilder();

    private final LeafBuilder artifactIdBuilder = new LeafBuilder();

    private final LeafBuilder versionBuilder = new LeafBuilder();

    XmlBuilder xmlBuilder = new XmlBuilder();


    public ImmPlugin2 build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();
        ImmGroupId groupId = null;
        ImmArtifactId artifactId = null;
        ImmVersion version = null;
        String configuration = null;
        String reportSets = null;


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
                        case "configuration":
                            configuration = xmlBuilder.build( node ) ;
                            break;
                        case "reportSets":
                            reportSets = xmlBuilder.build( node ) ;
                            break;
                    }
            }
        }

        return new ImmPlugin2( artifactId, groupId, version, configuration, reportSets );
    }
}
