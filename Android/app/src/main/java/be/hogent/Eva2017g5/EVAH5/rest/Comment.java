package be.hogent.Eva2017g5.EVAH5.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sofie.
 */

public class Comment {
    @SerializedName("_id")
    private long id;

    @SerializedName("author")
    private EvaUser author;
    @SerializedName("message")
    private String message;
    @SerializedName("upvotes")
    private int upvotes;
    @SerializedName("downvotes")
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
