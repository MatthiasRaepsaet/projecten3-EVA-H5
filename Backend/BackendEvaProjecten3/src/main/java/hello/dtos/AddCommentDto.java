package hello.dtos;

/**
 * Created by Matthias on 4/07/2017.
 */
public class AddCommentDto {
    private long userId;
    private String message;

    public AddCommentDto() {
    }

    public AddCommentDto(long userId, String message) {
        this.userId = userId;
        this.message = message;
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
}
