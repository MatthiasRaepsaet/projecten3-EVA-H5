package hello.dtos;

import hello.domain.EvaUser;

/**
 * Created by Matthias on 4/07/2017.
 */
public class CommentDto {
    private long id;
    private String author;

    private String message;
    private int upvotes;
    private int downvotes;

    public CommentDto() {
    }

    public CommentDto(long id, String author, String message, int upvotes, int downvotes) {

        this.id = id;
        this.author = author;
        this.message = message;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
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
