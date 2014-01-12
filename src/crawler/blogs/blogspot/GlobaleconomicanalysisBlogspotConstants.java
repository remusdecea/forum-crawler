package crawler.blogs.blogspot;

import crawler.blogs.Constants;

public class GlobaleconomicanalysisBlogspotConstants implements Constants {
    private final String POST_PAGE = "//div[@class='echocommentsclass echo-ui']/div[@class='echo-stream-container echo-primaryFont echo-primaryBackgroundColor']/div[@class='echo-stream-body']/div[@class='echo-item-content']";

    private final String POST_DATE = "//div[@class='blog-posts hfeed']/div[@class='date-header']";
    private final String POST_TITLE = "//div[@class='blog-posts hfeed']/div[@class='post']/h3[@class='post-title']/a";
    private final String POST_AUTHOR = "//div[@class='blog-posts hfeed']/div[@class='post']/div[@class='post-footer']/i";
    private final String POST_TEXT = "//div[@class='blog-posts hfeed']/div[@class='post']/h3[@class='post-title']/div[@class='post-body']";

    private final String POST_COMMENT_INIT = "//div[@class='echocommentsclass echo-ui']/div[@class='echo-stream-container echo-primaryFont echo-primaryBackgroundColor']/div[@class='echo-stream-body']/div[@class='echo-item-content']";
    private final String POST_COMMENT_AUTHOR = "/div[@class='echo-item-container echo-item-container-root-thread']/div/div/div/div/div[@class='echo-item-authorName echo-linkColor']";
    private final String POST_COMMENT_DATE = "/div[@class='echo-item-container echo-item-container-root-thread']/div/div/div/div/div[@class='echo-item-data']/div[@class='echo-item-body echo-primaryColor']/span";
    private final String POST_COMMENT_TEXT = "/div[@class='echo-item-container echo-item-container-root-thread']/div/div/div/div/div[@class='echo-item-footer echo-secondaryColor echo-secondaryFont']/div[@class='echo-item-date']";
    private final String POST_COMMENT_REPLIES = "/div[@echo-item-children]/div[@class='echo-item-content']";


    public String getPostCommentReplies() {
        return POST_COMMENT_REPLIES;
    }

    public String getPostCommentText() {
        return POST_COMMENT_TEXT;
    }

    public String getPostCommentDate() {
        return POST_COMMENT_DATE;
    }

    public String getPostCommentAuthor() {
        return POST_COMMENT_AUTHOR;
    }

    public String getPostCommentInit() {
        return POST_COMMENT_INIT;
    }

    public String getPostText() {
        return POST_TEXT;
    }

    public String getPostAuthor() {
        return POST_AUTHOR;
    }

    public String getPostTitle() {
        return POST_TITLE;
    }

    public String getPostDate() {
        return POST_DATE;
    }

    public String getPostPage() {
        return POST_PAGE;
    }








}
