package be.hogent.Eva2017g5.EVAH5.domainAndModel;

/**
 * Created by sofie.
 */

public class ChallengeUserDto {
    String challengeId;
    String userId;

    public ChallengeUserDto(String challengeId, String userId) {
        this.challengeId = challengeId;
        this.userId = userId;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
