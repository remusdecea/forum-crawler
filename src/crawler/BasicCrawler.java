package crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

public class BasicCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    @Override
    public boolean shouldVisit(WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith("http://automaticacalculatoare.wordpress.com/");
    }

    @Override
    public void visit(Page page) {

        String htmlContent = ((HtmlParseData) page.getParseData()).getHtml();
        if (htmlContent.contains("RESPONSES TO")) {
            return;
        }

        String wantedUrl = "http://automaticacalculatoare.wordpress.com/2013/05/26/end-of-an-era-dawn-of-another";


        System.err.println(page.getWebURL().toString());
        if (!page.getWebURL().toString()
                .contains(wantedUrl)) {
            return;
        }
        int index = 0;
        boolean inTag = false;
        StringBuilder strepBody = new StringBuilder(htmlContent);
        System.out.println(strepBody.toString());

        while (index < strepBody.length()) {
            if (inTag) {
                if (strepBody.charAt(index) == '>') {
                    inTag = false;
                }

                if (inTag &&
                        (strepBody.charAt(index) == ' ')
                        ) {
                    while (strepBody.charAt(index) != '>')
                        strepBody.deleteCharAt(index);
                    inTag = false;
                }
                index++;
            } else {
                if (strepBody.charAt(index) == '<' && strepBody.charAt(index + 1) != '!') {
                    inTag = true;
                    index++;
                } else {
                    strepBody.deleteCharAt(index);
                }
            }
        }

        System.out.println(strepBody.toString());
    }
}