package parser;

import org.w3c.dom.Document;

public interface ParseStrategy {

	public Document getComments(String url);
}
