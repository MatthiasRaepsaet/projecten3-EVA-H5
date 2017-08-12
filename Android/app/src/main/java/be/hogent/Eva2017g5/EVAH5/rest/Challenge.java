package be.hogent.Eva2017g5.EVAH5.rest;

/**
 * Created by sofie.
 */

public class Challenge {
    private long id;

    private String title;
    private String description;
    private String difficulty;
    private String contribution;

    private int points;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getContribution() {
        return contribution;
    }

    public int getPoints() {
        return points;
    }
}
