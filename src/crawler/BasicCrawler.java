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
        if (htmlContent.contains("textarea")) {
            System.err.println(page.getWebURL().toString());

            int index = 0;
            boolean inTag = false;
            StringBuilder strepBody = new StringBuilder(htmlContent);
            System.out.println(strepBody.toString());

            while (index < strepBody.length()) {
                if (inTag) {
                    if (strepBody.charAt(index) == '>') {
                        inTag = false;
                    }
                    index++;
                } else {
                    if (strepBody.charAt(index) == '<') {
                        inTag = true;
                        index++;
                    } else {
                        strepBody.deleteCharAt(index);
                    }
                }
            }


            System.out.println(strepBody.toString());

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
//        int docid = page.getWebURL().getDocid();
//        String url = page.getWebURL().getURL();
//        String domain = page.getWebURL().getDomain();
//        String path = page.getWebURL().getPath();
//        String subDomain = page.getWebURL().getSubDomain();
//        String parentUrl = page.getWebURL().getParentUrl();
//        String anchor = page.getWebURL().getAnchor();
//
//        System.out.println("Docid: " + docid);
//        System.out.println("URL: " + url);
//        System.out.println("Domain: '" + domain + "'");
//        System.out.println("Sub-domain: '" + subDomain + "'");
//        System.out.println("Path: '" + path + "'");
//        System.out.println("Parent page: " + parentUrl);
//        System.out.println("Anchor text: " + anchor);
//
//        if (page.getParseData() instanceof HtmlParseData) {
//            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
//            String text = htmlParseData.getText();
//            String html = htmlParseData.getHtml();
//            List<WebURL> links = htmlParseData.getOutgoingUrls();
//
//            System.out.println("Text length: " + text.length());
//            System.out.println("Html length: " + html.length());
//            System.out.println("Number of outgoing links: " + links.size());
//        }
//
//        Header[] responseHeaders = page.getFetchResponseHeaders();
//        if (responseHeaders != null) {
//            System.out.println("Response headers:");
//            for (Header header : responseHeaders) {
//                System.out.println("\t" + header.getName() + ": " + header.getValue());
//            }
//        }
//
//        System.out.println("=============");
    }
}