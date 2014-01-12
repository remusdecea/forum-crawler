package crawler.blogs;

import crawler.dto.Comment;
import crawler.dto.Post;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.util.ArrayList;

public class BlogParser {

    public Post parse(TagNode webPage, Constants constants) {
        try {
            if (!isPostPage(webPage, constants))
                return null;

            String date = getItemText(webPage, constants.getPostDate());
            String title = getItemText(webPage, constants.getPostTitle());
            String author = getItemText(webPage, constants.getPostAuthor());
            String text = getItemText(webPage, constants.getPostText());
            ArrayList<Comment> comment = parseComments(webPage, constants);

            return new Post(author, title, date, text, comment);

        } catch (XPatherException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Comment> parseComments(TagNode webPage, Constants constants) throws XPatherException {
        ArrayList<Comment> commentList = new ArrayList<Comment>();

        Object[] comments = getItem(webPage, constants.getPostCommentInit());
        for (Object commentObj : comments) {
            TagNode comment = (TagNode) commentObj;

            Comment commentResult = new Comment();
            commentList.add(commentResult);
            initComment(comment, commentResult, constants);
            parseCommentReplies(comment, commentResult, constants);
        }

        return commentList;
    }

    private void parseCommentReplies(TagNode comment, Comment commentResult, Constants constants) throws XPatherException {
        Comment commentReplyResult = new Comment();

        Object[] commentsReplies = getItem(comment, constants.getPostCommentReplies());
        for (Object commentReplyObj : commentsReplies) {
            TagNode commentReply = (TagNode) commentReplyObj;
            initComment(comment, commentReplyResult, constants);
            commentResult.replies.add(commentReplyResult);
            parseCommentReplies(commentReply, commentReplyResult, constants);
        }
    }

    private void initComment(TagNode comment, Comment commentResult, Constants constants) throws XPatherException {
        commentResult.owner = getItemText(comment, constants.getPostCommentAuthor());
        commentResult.postedDate = getItemText(comment, constants.getPostCommentDate());
        commentResult.text = getItemText(comment, constants.getPostCommentText());
        commentResult.replies = new ArrayList<Comment>();
    }


    private Object[] getItem(TagNode webPage, String pattern) throws XPatherException {
        return webPage.evaluateXPath(pattern);
    }

    private String getItemText(TagNode webPage, String pattern) throws XPatherException {
        return ((TagNode) (webPage.evaluateXPath(pattern))[0]).getText().toString();
    }

    private boolean isPostPage(TagNode webPage, Constants constants) throws XPatherException {
        return webPage.evaluateXPath(constants.getPostPage()).length != 0;
    }

}
