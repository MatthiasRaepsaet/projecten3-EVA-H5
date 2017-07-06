package hello.dtos;

import hello.domain.Comment;
import hello.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias on 4/07/2017.
 */
public class RecipeDto {
    private long id;

    private String title;
    private String author;
    private String description;
    private List<Ingredient> ingredients;
    private int upvotes;
    private int downvotes;
    private List<CommentDto> comments = new ArrayList<>();

    public RecipeDto(long id, String title, String author, String description, List<Ingredient> ingredients, int upvotes, int downvotes, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.ingredients = ingredients;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.comments = comments;
    }

    public RecipeDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
