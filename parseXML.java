/*
   This code parses b.xml to get the location of another file a.xml.
   The file a.xml contains a tree, this code parses a.xml to create that tree.
 */

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
		
		
		//reading b.xml to get the location of a.xml
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
					//System.out.println(xmlFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		//parsing a.xml
		
		/*
		   The while loop updates the XPATH to get the nodes at level 0 then level 1 and so on .... 
		   it breaks out of the while loop when there are no nodes found at some level.
		   */
		
		XPATH="";
		String elementAttribute="";
		String elementValue="";
		int level = 0;
		
		while(true)
		{
			XPATH+="/node";
			try {
				builder = docFactory.newDocumentBuilder();
				Document doc = builder.parse(xmlFile);
				XPathExpression expr = XPathFactory.newInstance().newXPath().compile(XPATH);
				Object hits = expr.evaluate(doc, XPathConstants.NODESET ) ;
				if ( hits instanceof NodeList ) {
					NodeList list = (NodeList) hits ;
					if(list.getLength()==0)
						break;
					System.out.println("\n\nAt level "+level+" ....");
					for (int i = 0; i < list.getLength(); i++ ) {
						Element element = (Element) list.item(i);
						elementAttribute = element.getAttribute("attribute");
						System.out.println(elementAttribute);
						elementValue = element.getAttribute("value");
						System.out.println(elementValue);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			level++;
		}
	}
}
