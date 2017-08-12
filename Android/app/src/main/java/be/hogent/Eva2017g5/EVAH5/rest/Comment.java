package be.hogent.Eva2017g5.EVAH5.rest;

/**
 * Created by sofie.
 */

public class Comment {
    private long id;

    private EvaUser author;
    private String message;
    private int upvotes;
    private int downvotes;

    public long getId() {
        return id;
    }

    public EvaUser getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }
}
