package crawler.blogs.blogspot;

import crawler.blogs.Constants;

public class NotthetreasuryviewBlogspotConstants implements Constants {
    private final String POST_PAGE = "//div[@class='comments'][@id='comments']/h4";

    private final String POST_DATE = "//div[@class='date-outer']/h2[@class='date-header']/span";
    private final String POST_TITLE = "//div[@class='post hentry']/h3[@class='post-title entry-title']";
    private final String POST_AUTHOR = "//span[@class='fn'][@itemprop='author']/a[@rel='author'][@title='author profile']/span[@itemprop='name']";
    private final String POST_TEXT = "//div[@class='post-body entry-content'][@itemprop='description articleBody']";

    private final String POST_COMMENT_INIT = "//div[@class='comment-thread']/ol/li";
    private final String POST_COMMENT_AUTHOR = "/div[@class='comment-block']/div[@class='comment-header']/cite[@class='user']/a";
    private final String POST_COMMENT_DATE = "/div[@class='comment-block']/div[@class='comment-header']/span[@class='datetime secondary-text']/a";
    private final String POST_COMMENT_TEXT = "/div[@class='comment-block']/p[@class='comment-content']";
    private final String POST_COMMENT_REPLIES = "/div[@class='comment-replies']/span/div[@class='comment-thread inline-thread']/ol/div/li";


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
