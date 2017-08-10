package be.sofie.eva.eva.rest;

import java.util.List;

/**
 * Created by sofie.
 */

public class Recipe {
    private String id;
    private String title;
    private String description;
    private EvaUser author;
    private List<Ingredient> ingredients;
    private int upvotes;
    private int downvotes;
    private List<Comment> comments;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
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
}
