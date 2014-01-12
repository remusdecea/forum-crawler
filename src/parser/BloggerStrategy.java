package parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.jaunt.UserAgent;

public class BloggerStrategy implements ParseStrategy {

	@Override
	public Document getComments(String url) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			// root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("comments");
			Attr urlAttribute = doc.createAttribute("url");
			urlAttribute.setValue(url);

			UserAgent userAgent = new UserAgent();
			userAgent.visit(url);
			com.jaunt.Elements headersElements = userAgent.doc.findFirst(
					"<dl id=comments-block>").findEach("<dt>");
			for (com.jaunt.Element e : headersElements) {
				
				System.out.println("id " + e.getAt("id"));
				com.jaunt.Element spanElement = userAgent.doc.findFirst(
						"<dt id=" +e.getAt("id")+">").findFirst("<span dir=ltr>");
				System.out.println(spanElement);
//				
//				com.jaunt.Element spanElement = e.findFirst("<span>");
//				System.out.println(spanElement.getText());
				// TODO:
			}

			// comment block element

			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
