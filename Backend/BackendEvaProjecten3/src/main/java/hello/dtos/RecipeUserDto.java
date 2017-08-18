package hello.dtos;

/**
 * Created by Matthias on 16/08/2017.
 */
public class RecipeUserDto {
    private long userId;
    private long recipeId;

    public RecipeUserDto() {
    }

    public RecipeUserDto(long userId, long recipeId) {
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}
