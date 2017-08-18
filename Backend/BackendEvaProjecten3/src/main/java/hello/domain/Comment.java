package hello.domain;

import javax.persistence.*;

/**
 * Created by Matthias on 4/07/2017.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String author;

    private String message;
    private int upvotes;
    private int downvotes;

    public Comment() {
        this.downvotes = 0;
        this.upvotes = 0;
    }

    public Comment(String author, String message) {
        this.author = author;
        this.message = message;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
