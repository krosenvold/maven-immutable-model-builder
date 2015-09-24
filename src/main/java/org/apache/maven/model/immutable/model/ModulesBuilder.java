package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

public class ModulesBuilder
{
    private final LeafBuilder propVal = new LeafBuilder();


    public List<String> build( XMLStreamReader2 node )
        throws XMLStreamException
    {
        int startLevel = node.getDepth();

        List<String> modules = new ArrayList<>();

        while ( node.hasNext() && node.getDepth() >= startLevel )
        {
            int eventType = node.next();
            switch ( eventType )
            {
                case XMLStreamReader2.START_ELEMENT:
                    String localName = node.getLocalName();
                    switch ( localName )
                    {
                        case "module":
                            modules.add( propVal.singleTextValue( node ) );
                            break;
                        default:
                            throw new RuntimeException( "Unsupported child tag" + localName );
                    }
            }

        }


        return modules;
    }

}
