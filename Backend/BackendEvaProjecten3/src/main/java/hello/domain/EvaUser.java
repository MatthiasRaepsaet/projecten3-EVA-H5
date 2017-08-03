package hello.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Matthias on 3/07/2017.
 */
@Entity
public class EvaUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private int score;

    @OneToMany
    private List<Challenge> completedChallenges;
    @OneToMany
    private List<Challenge> todaysChallenges;

    @OneToMany
    private List<Recipe> myRecipes;

    @ManyToMany
    private List<Recipe> favoriteRecipes;

    public EvaUser(String firstName, String lastName, String email, String username, String password, int score, List<Challenge> completedChallenges, List<Challenge> todaysChallenges, List<Recipe> myRecipes, List<Recipe> favoriteRecipes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.score = score;
        this.completedChallenges = completedChallenges;
        this.todaysChallenges = todaysChallenges;
        this.myRecipes = myRecipes;
        this.favoriteRecipes = favoriteRecipes;
    }

    public EvaUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Challenge> getCompletedChallenges() {
        return completedChallenges;
    }

    public void setCompletedChallenges(List<Challenge> completedChallenges) {
        this.completedChallenges = completedChallenges;
    }

    public List<Challenge> getTodaysChallenges() {
        return todaysChallenges;
    }

    public void setTodaysChallenges(List<Challenge> todaysChallenges) {
        this.todaysChallenges = todaysChallenges;
    }

    public List<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public void setMyRecipes(List<Recipe> myRecipes) {
        this.myRecipes = myRecipes;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
