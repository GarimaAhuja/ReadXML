import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class parseXML 
{	
	public static void main(String[] args) 
	{
		String locationFile = "b.xml" ;
		String XPATH = "location";
		String xmlFile = null;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(true);
		DocumentBuilder builder;
		try {
			builder = docFactory.newDocumentBuilder();
			Document doc = builder.parse(locationFile);
			XPathExpression expr = XPathFactory.newInstance().newXPath().compile(XPATH);
			Object hits = expr.evaluate(doc, XPathConstants.NODESET ) ;
			if ( hits instanceof NodeList ) {
				NodeList list = (NodeList) hits ;
				for (int i = 0; i < list.getLength(); i++ ) {
					Element element = (Element) list.item(i);
					xmlFile = element.getTextContent();
					System.out.println(xmlFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
