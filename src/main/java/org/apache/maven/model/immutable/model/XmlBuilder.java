package org.apache.maven.model.immutable.model;

import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamException;
import java.util.Stack;

public class XmlBuilder {

	public String build( XMLStreamReader2 node )
			throws XMLStreamException
	{
		final StringBuilder sb = new StringBuilder();
		int startLevel = node.getDepth();
		Stack<String> elems = new Stack<>();

		int eventType = node.getEventType();

		do
		{

			switch ( eventType )
			{
				case XMLStreamReader2.START_ELEMENT:
					String localName = node.getLocalName();
					if ( node.getDepth() > elems.size()) sb.append("\n");
					elems.push(localName);
					for (int j = 0; j < (node.getDepth() - startLevel); j++) sb.append( "  ");
					sb.append("<").append(localName);
					final int attributeCount = node.getAttributeCount();
					for (int i = 0; i < attributeCount; i++){
						sb.append(" ").append(node.getAttributeName(i)).append("=\"").append( node.getAttributeValue(i)).append("=\"");
					}
					sb.append(">");
					break;
				case XMLStreamReader2.END_ELEMENT:
					sb.append("</").append(elems.size() > 0 ? elems.pop() : "BugubU").append(">\n");
					break;
				case XMLStreamReader2.CHARACTERS:
					sb.append(org.codehaus.plexus.util.StringUtils.trim(node.getText()));
					break;
			}
			eventType = node.next();
		} while( node.hasNext() && node.getDepth() >= startLevel );
		return sb.toString();
	}

}
