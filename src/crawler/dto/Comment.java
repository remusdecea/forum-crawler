package crawler.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Comment implements Serializable{

    public String owner;
    public String postedDate;

    public String text;
    public ArrayList<Comment> replies;

    public Comment() {
        replies = new ArrayList<Comment>();
    }
}
