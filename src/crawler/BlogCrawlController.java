package crawler;

import crawler.dto.Post;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class BlogCrawlController {

    public static final String CRAWL_DATA = "./crawlData/";
    public static final int NUMBER_OF_CRAWLERS = 5;

    public static final String[] BLOGS_ALLOWED  = {
                        "http://globaleconomicanalysis.blogspot.de/",
//            "http://notthetreasuryview.blogspot.de/"
    };

    public static final ArrayList<Post> allPosts = new ArrayList<Post>();

    public static final String[] BLOGS_ALLOWED_ALL  = {

            //--- economie:",
//            "http://mjperry.blogspot.com/",
//            "http://globaleconomicanalysis.blogspot.de/",
            "http://notthetreasuryview.blogspot.de/",
//            "http://noahpinionblog.blogspot.de/",
//            "http://rwer.wordpress.com/",
//            "http://unlearningeconomics.wordpress.com/",

            // "--- politica:",
//            "http://plainblogaboutpolitics.blogspot.de/",
//            "http://2politicaljunkies.blogspot.de/",
//            "http://rutherfordl.wordpress.com/",
//            "http://mikk2.wordpress.com/",
//            "http://munguinsrepublic.blogspot.de/"
    };


    public static void main(String[] args) throws Exception {

        String crawlStorageFolder = CRAWL_DATA;

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(10);
        config.setMaxDepthOfCrawling(20);
        config.setMaxPagesToFetch(1000);
        config.setResumableCrawling(false);

        config.setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0");
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        for (String blogUrl : BLOGS_ALLOWED){
            controller.addSeed(blogUrl);
        }
        controller.start(BlogCrawler.class, NUMBER_OF_CRAWLERS);


        ObjectMapper jsonMapper = new ObjectMapper();
        System.out.println(jsonMapper.writeValueAsString(allPosts));
    }
}