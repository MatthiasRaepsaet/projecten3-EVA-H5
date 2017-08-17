package be.hogent.Eva2017g5.EVAH5.rest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sofie.
 */

public class Recipe {
    @SerializedName("_id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("author")
    private EvaUser author;
    @SerializedName("ingredients")
    private List<Ingredient> ingredients;
    @SerializedName("upvotes")
    private int upvotes;
    @SerializedName("downvotes")
    private int downvotes;
    @SerializedName("comments")
    private List<Comment> comments;

    private String votes;

    public Recipe(String title, String description, String votes){
        this.title = title;
        this.description = description;
        this.votes = votes;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description.substring(0, 100);
    }

    public EvaUser getAuthor() {
        return author;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getVotes(){
        return ""+ getDownvotes() + getUpvotes();
    }



}
