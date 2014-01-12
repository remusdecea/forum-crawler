package crawler;

import crawler.blogs.BlogParser;
import crawler.blogs.blogspot.GlobaleconomicanalysisBlogspotConstants;
import crawler.blogs.blogspot.NotthetreasuryviewBlogspotConstants;
import crawler.dto.Post;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.util.regex.Pattern;

public class BlogCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|bmp|gif|jpe?g"
                    + "|png|tiff?|mid|mp2|mp3|mp4"
                    + "|wav|avi|mov|mpeg|ram|m4v|pdf"
                    + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    private BlogParser blogController = new BlogParser();
    ;

    @Override
    public boolean shouldVisit(WebURL url) {
        String href = url.getURL().toLowerCase();
        for (String blogsAllowed : BlogCrawlController.BLOGS_ALLOWED) {
            if (href.startsWith(blogsAllowed)) {
                return !FILTERS.matcher(href).matches();
            }
        }

        //Not a page we are interested
        return false;
    }

    @Override
    public void visit(Page page) {
        HtmlParseData parseData = (HtmlParseData) page.getParseData();

        System.out.println(page.getWebURL().toString());
        String html = parseData.getHtml().replace("\n", "");

        CleanerProperties props = new CleanerProperties();
        props.setOmitComments(true);
        TagNode tagNode = new HtmlCleaner(props).clean(html);


        if (page.getWebURL().toString().contains("notthetreasuryview")) {
            Post post = blogController.parse(tagNode, new NotthetreasuryviewBlogspotConstants());
            if (post != null)
                BlogCrawlController.allPosts.add(post);
        }

        if (page.getWebURL().toString().contains("globaleconomicanalysis")) {
            Post post = blogController.parse(tagNode, new GlobaleconomicanalysisBlogspotConstants());
            if (post != null)
                BlogCrawlController.allPosts.add(post);
        }

    }
}