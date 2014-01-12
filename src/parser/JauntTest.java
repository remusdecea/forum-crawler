package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.jaunt.*;

public class JauntTest {

	private static void getContent(String url) {
		
		UserAgent userAgent = new UserAgent();
		List<String> commentLinks = new ArrayList<String>();
		List<Integer> numComments = new ArrayList<Integer>();
		
		try {
			userAgent.visit(url);
			Elements elements = userAgent.doc.findEvery("<a>Comments|comments");
			for(Element e : elements) {
				
				commentLinks.add(e.getAttx("href").replaceAll("&amp;", "&"));
				String text = e.getText();
				StringTokenizer st = new StringTokenizer(text);
				
				try {
					int num = Integer.parseInt(st.nextToken());
					numComments.add(num);
				} catch(NumberFormatException nfe) {
					numComments.add(0);
				}
			}
		} catch (ResponseException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < commentLinks.size(); i++) {
			
			System.out.println(numComments.get(i) + " " + commentLinks.get(i));
			if(commentLinks.get(i).contains("blogger")){
				BloggerHtmlUnit blogger = new BloggerHtmlUnit();
				try {
					blogger.getComments(commentLinks.get(i));
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			if(commentLinks.get(i).contains("blogspot") ){
				BlogspotStrategy blogger = new BlogspotStrategy();
				blogger.getComments(commentLinks.get(i));
			}
		}
		

	}
	
	public static void main(String [] args) {
		
		// yes I am THAT lazy. will read from file later
		List<String> urls = new ArrayList<String>();
		urls.add("http://mjperry.blogspot.ro/");
		urls.add("http://notthetreasuryview.blogspot.de/");
		urls.add("http://noahpinionblog.blogspot.de/");
		urls.add("http://rwer.wordpress.com/");
		urls.add("http://unlearningeconomics.wordpress.com/");
		for(String url : urls)
			getContent(url);
		
	}
}
