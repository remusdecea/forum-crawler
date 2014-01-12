package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class BloggerHtmlUnit {

	public Document getComments(String url) throws FailingHttpStatusCodeException, IOException{
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		final HtmlPage page = webClient.getPage(url);
//		final DomElement commentsList = page.getElementById("comments-block");
		//System.out.println(commentsList.asText());
		
		List<DomElement> authorElements= (List<DomElement>) page.getByXPath("//dl[@id='comments-block']//dt//a");
		List<DomElement> timeStampElements  = (List<DomElement>) page.getByXPath("//dl[@id='comments-block']//dd//p[@class='comment-timestamp']");
		List<DomElement> commentElements = (List<DomElement>) page.getByXPath("//dl[@id='comments-block']//dd//p[not(@class='comment-timestamp')]");
		List<String> authors = new ArrayList<String>();
		List<String> timeStamps = new ArrayList<String>();
		List<Integer> removedCommentsIndexes = new ArrayList<Integer>();
		List<String> comments = new ArrayList<String>();
		
		for(int i = 0; i < authorElements.size(); i++){
			String authorName = authorElements.get(i).asText();
			if(!authorName.equals("") && authorName !=null){
				authors.add(authorName);
			}
		}
		for(int i = 0; i < timeStampElements.size(); i++){
			timeStamps.add(timeStampElements.get(i).asText());
		}
		for(int i = 0; i < commentElements.size(); i++){
			String comment = commentElements.get(i).asText();
			if(comment.equals("This comment has been removed by the author.")){
				removedCommentsIndexes.add(i);
			}else{
				comments.add(commentElements.get(i).asText());
			}
		}
		
		//Now remove timestamps from collection for elements that have been deleted 
		for(int i = removedCommentsIndexes.size()-1; i >= 0; i--){
			timeStamps.remove(removedCommentsIndexes.get(i));
		}
		
		
		return null;
	}
	
}
