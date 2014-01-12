package crawler.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Post implements Serializable{
    public String owner;
    public String title;
    public String postedDate;
    public String text;
    public ArrayList<Comment> comments;

    public Post(String owner, String title, String postedDate, String text, ArrayList<Comment> comments) {
        this.owner = owner;
        this.title = title;
        this.postedDate = postedDate;
        this.text = text;
        this.comments = comments;
    }
}
