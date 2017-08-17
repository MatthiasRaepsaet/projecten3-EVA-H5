package be.hogent.Eva2017g5.EVAH5.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sofie.
 */

public class Challenge {
    @SerializedName("_id")
    private long id;

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("difficulty")
    private String difficulty;
    @SerializedName("contribution")
    private String contribution;

    @SerializedName("points")
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
