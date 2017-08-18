package hello.dtos;

/**
 * Created by Matthias on 18/08/2017.
 */
public class ChallengeUserDto {

    private long userId;
    private long challengeId;

    public ChallengeUserDto(long userId, long challengeId) {
        this.userId = userId;
        this.challengeId = challengeId;
    }

    public ChallengeUserDto() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(long challengeId) {
        this.challengeId = challengeId;
    }
}
