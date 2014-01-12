package parser;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.jaunt.UserAgent;

public class BlogspotStrategy implements ParseStrategy {

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
			com.jaunt.Elements spansElement = userAgent.doc
					.findEvery("<div class=comment-block>");
			ArrayList<String> users = new ArrayList<String>();
			ArrayList<String> dates = new ArrayList<String>();
			ArrayList<String> comments = new ArrayList<String>();
			for (com.jaunt.Element e : spansElement) {
				String user = e.findFirst("<cite>").getText();
				if (user.equals("")) {
					String user2 = e.findFirst("<cite>").findFirst("<a>")
							.getText();
					users.add(user2);
				} else {
					users.add(user);
				}
				String date = e.findEach("<span>").getElement(1)
						.findFirst("<a>").getText();
				dates.add(date);

				String commentText = e.findFirst("<p class = comment-content>")
						.getText();
				comments.add(commentText);
			}
			System.out.println(users);
			System.out.println(dates);
			// System.out.println(comments);
			System.out.println(users.size() == dates.size()
					&& dates.size() == comments.size());

			// comment block element
			// TODO: build document here and return it
			
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
