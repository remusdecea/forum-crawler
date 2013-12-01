package crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class BasicCrawlController {

    public static void main(String[] args) throws Exception {
        args = new String[]{
                "/Users/mciorobe/work/mihai/java/forum-crawler/crawlData/",
                "1"};
        if (args.length != 2) {
            System.out.println("Needed parameters: ");
            System.out.println("\t rootFolder (it will contain intermediate crawl data)");
            System.out.println("\t numberOfCralwers (number of concurrent threads)");
            return;
        }

        String crawlStorageFolder = args[0];
        int numberOfCrawlers = Integer.parseInt(args[1]);

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);

		/*
         * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
        config.setPolitenessDelay(10);

		/*
		 * You can set the maximum crawl depth here. The default value is -1 for
		 * unlimited depth
		 */
        config.setMaxDepthOfCrawling(20);

		/*
		 * You can set the maximum number of pages to crawl. The default value
		 * is -1 for unlimited number of pages
		 */
        config.setMaxPagesToFetch(1000);

		/*
		 * Do you need to set a proxy? If so, you can use:
		 * config.setProxyHost("proxyserver.example.com");
		 * config.setProxyPort(8080);
		 * 
		 * If your proxy also needs authentication:
		 * config.setProxyUsername(username); config.getProxyPassword(password);
		 */

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
        config.setResumableCrawling(false);

        config.setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:25.0) Gecko/20100101 Firefox/25.0");
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);


        controller.addSeed("http://automaticacalculatoare.wordpress.com");
        controller.addSeed("http://automaticacalculatoare.wordpress.com/2013/10/09/cateva-lucruri-mai-putin-stiute-despre-participarea-profilor-la-awg");
        controller.addSeed("http://automaticacalculatoare.wordpress.com/2013/11/01/romanisme-nerespectarea-regulilor");

        controller.start(BasicCrawler.class, numberOfCrawlers);
    }
}