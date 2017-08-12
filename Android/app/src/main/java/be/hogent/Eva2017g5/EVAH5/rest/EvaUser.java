package be.hogent.Eva2017g5.EVAH5.rest;

import java.util.List;

/**
 * Created by sofie.
 */

public class EvaUser {
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private List<Challenge> completedChallenges;

    private List<Challenge> todaysChallenges;

    private List<Recipe> myRecipes;

    private List<Recipe> favoriteRecipes;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Challenge> getCompletedChallenges() {
        return completedChallenges;
    }

    public List<Challenge> getTodaysChallenges() {
        return todaysChallenges;
    }

    public List<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }
}
