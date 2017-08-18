package hello.dtos;

/**
 * Created by Matthias on 4/07/2017.
 */
public class AddCommentDto {
    private long userId;
    private String message;
    private long recipeId;

    public AddCommentDto() {
    }

    public AddCommentDto(long userId, String message, long recipeId) {
        this.userId = userId;
        this.message = message;
        this.recipeId = recipeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}
