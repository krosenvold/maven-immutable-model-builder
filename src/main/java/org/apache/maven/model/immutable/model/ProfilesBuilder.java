package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristian on 24.09.15.
 */
public class ProfilesBuilder
{
    private final ProfileBuilder profileBuilder;

    public ProfilesBuilder( BuildBuilder build, ReportingBuilder reportingBuilder )
    {
        profileBuilder = new ProfileBuilder( build, reportingBuilder );

    }


    public List<Profile> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<Profile> profiles = new ArrayList<>();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    if ( "profile".equals( node.getLocalName() ) )
                    {
                        profiles.add( profileBuilder.build( node ) );
                    }
                    else
                    {
                        throw new IllegalArgumentException( "Unsupported tag " + localName );
                    }

            }
        }
        return profiles;
    }
}
