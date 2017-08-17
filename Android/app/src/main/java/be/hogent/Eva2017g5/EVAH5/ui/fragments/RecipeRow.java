package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import java.util.List;

import be.hogent.Eva2017g5.EVAH5.rest.Comment;
import be.hogent.Eva2017g5.EVAH5.rest.EvaUser;
import be.hogent.Eva2017g5.EVAH5.rest.Ingredient;

/**
 * Created by sofie.
 */

public class RecipeRow {
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
