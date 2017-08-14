package be.hogent.Eva2017g5.EVAH5.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by chaitanya on 8/13/17.
 */

public class VeganChallenge {

    //properties

    private Date startDate;

    private int recipesMade;
    private int restaurantsVisited;
    private int quizzesTaken;

    private List<Challenge> challengesList;
    private Challenge currentChallenge;

    //constructor
    public VeganChallenge() {
        this.startDate = Calendar.getInstance().getTime();
        this.recipesMade = 0;
        this.restaurantsVisited = 0;
        this.quizzesTaken = 0;

        this.challengesList = new ArrayList<>();
    }

    //getter and setter


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getRecipesMade() {
        return recipesMade;
    }
    public void setRecipesMade(int recipesMade) {
        this.recipesMade = recipesMade;
    }

    public int getRestaurantsVisited() {
        return restaurantsVisited;
    }
    public void setRestaurantsVisited(int restaurantsVisited) {
        this.restaurantsVisited = restaurantsVisited;
    }

    public int getQuizzesTaken() {
        return quizzesTaken;
    }
    public void setQuizzesTaken(int quizzesTaken) {
        this.quizzesTaken = quizzesTaken;
    }

    public List<Challenge> getChallengesList() {
        return challengesList;
    }
    public void setChallengesList(List<Challenge> challengesList) {
        this.challengesList = challengesList;
    }

    public Challenge getCurrentChallenge() {
        return currentChallenge;
    }
    public void setCurrentChallenge(Challenge currentChallenge) {
        this.currentChallenge = currentChallenge;
    }

    //add to and remove from list

    public void addChallenge(Challenge c){
        challengesList.add(c);
    }
    public void removeChallenge(Challenge c){
        for (int i = 0; i < challengesList.size(); i++) {
            Challenge x = challengesList.get(i);
            if (x.challengeDate.getTime() == (c.challengeDate.getTime())) {
                challengesList.remove(x);
                break;
            }
        }
    }

    //increase and decrease parameters

    public void incrementRestaurantsVisited(){
        this.restaurantsVisited++;
    }
    public void decrementRestaurantsVisited(){
        this.restaurantsVisited--;
    }

    public void incrementRecipesMade(){
        this.recipesMade++;
    }
    public void decrementRecipesMade(){
        this.recipesMade--;
    }

    public void incrementQuizzesTaken(){
        this.quizzesTaken++;
    }
    public void decrementQuizzesTaken(){
        this.quizzesTaken--;
    }

    //properties to calculate stats (vegancalculator.com & cowspiracy.com/facts)

    public int getWaterReduced() {
        return getDaysSinceStart()*4164;

    }
    public int getCarbonReduced() {
        return getDaysSinceStart()*20;
    }
    public double getForestSaved(){
        return getDaysSinceStart()*2.79;
    }

    public int getDaysSinceStart(){
        //get current date
        Date currentDate= Calendar.getInstance().getTime();
        //no of days since dare start
        long msDiff = Calendar.getInstance().getTimeInMillis() - startDate.getTime();
        int days = (int) TimeUnit.MILLISECONDS.toDays(msDiff);

        return days;
    }
}
