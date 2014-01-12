package crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class BasicCrawlController {

    public static final String CRAWL_DATA = "../crawlData/";
    public static final int NUMBER_OF_CRAWLERS = 5;



    public static void main(String[] args) throws Exception {

        String crawlStorageFolder = CRAWL_DATA;
        int numberOfCrawlers = NUMBER_OF_CRAWLERS;

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

        String wantedUrl = "http://automaticacalculatoare.wordpress.com/2013/05/26/end-of-an-era-dawn-of-another";

        controller.addSeed(wantedUrl);
        controller.start(BasicCrawler.class, numberOfCrawlers);
    }
}