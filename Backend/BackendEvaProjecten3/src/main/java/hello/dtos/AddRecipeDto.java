package hello.dtos;

import hello.domain.Ingredient;

import java.util.List;

/**
 * Created by Matthias on 4/07/2017.
 */
public class AddRecipeDto {
    private String title;
    private String description;
    private long userId;
    private List<Ingredient> ingredients;

    public AddRecipeDto(String title, String description, long userId, List<Ingredient> ingredients) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.ingredients = ingredients;
    }

    public AddRecipeDto() {

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
