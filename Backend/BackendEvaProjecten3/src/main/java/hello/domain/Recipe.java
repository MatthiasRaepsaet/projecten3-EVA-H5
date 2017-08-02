package hello.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias on 4/07/2017.
 */
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String title;
    private String description;

    @ManyToOne
    private EvaUser author;

    @ManyToMany
    private List<Ingredient> ingredients;
    private int upvotes;
    private int downvotes;

    private String category;

    @OneToMany
    private List<Comment> comments;

    public Recipe() {
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public Recipe(String title, String description, EvaUser author, List<Ingredient> ingredients, String category) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.ingredients = ingredients;
        this.upvotes = 0;
        this.downvotes = 0;;
        this.category = category;
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

    public EvaUser getAuthor() {
        return author;
    }

    public void setAuthor(EvaUser author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
