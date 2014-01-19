package crawler.polycafe;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PolycafeFormatter {

	public static void format(String data) {

		try {
			JSONArray jsonArray = new JSONArray(data);
			ArrayList<Document> documents = new ArrayList<>();
			for (int i = 0; i < jsonArray.length(); i++) {
				documents.add(getDocument(jsonArray.getJSONObject(i)));
			}
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			for( int i = 0; i < documents.size(); i++){
					File file = new File("chat"+i+".xml");
				if(!file.exists()){
					DOMSource source = new DOMSource(documents.get(i));
					StreamResult result = new StreamResult(new File("chat"+i+".xml").getAbsolutePath());
					transformer.transform(source, result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private static Document getDocument(JSONObject jsonObject)
			throws ParserConfigurationException, DOMException, JSONException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element dialogElement = doc.createElement("Dialog");
		doc.appendChild(dialogElement);
		Element topicsElement = doc.createElement("Topics");
		topicsElement.setTextContent(jsonObject.getString("title"));
		Element participantsElement = doc.createElement("Participants");
		
		ArrayList<String> participants = new ArrayList<>();
		Element bodyElement = doc.createElement("Body");
		dialogElement.appendChild(topicsElement);
		JSONArray commentsArray = jsonObject.getJSONArray("comments");
		for (int i = 0; i < commentsArray.length(); i++) {
			Element turnElement = doc.createElement("Turn");
			turnElement.setAttribute("nickname", commentsArray.getJSONObject(i)
					.getString("owner"));
			Element uttElement = doc.createElement("Utterance");
			uttElement.setAttribute("genid", "" + (i + 1));
			uttElement.setAttribute("time", commentsArray.getJSONObject(i)
					.getString("postedDate"));
			uttElement.setTextContent(commentsArray.getJSONObject(i).getString(
					"text"));
			if(!participants.contains(commentsArray.getJSONObject(i)
					.getString("owner"))){
				participants.add(commentsArray.getJSONObject(i)
					.getString("owner"));
			}
			turnElement.appendChild(uttElement);
			bodyElement.appendChild(turnElement);
			
		}
		for(int i =0; i < participants.size(); i++){
			Element personElement = doc.createElement("Person");
			personElement.setAttribute("nickname", participants.get(i));
			participantsElement.appendChild(personElement);
			
		}
		
		dialogElement.appendChild(topicsElement);
		dialogElement.appendChild(participantsElement);
		dialogElement.appendChild(bodyElement);
		return doc;
	}
}
