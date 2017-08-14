package be.hogent.Eva2017g5.EVAH5.domain;

/**
 * Created by chaitanya on 8/13/17.
 */

public class RecipeChallenge extends Challenge {


    private int chosenRecipeId;


    public RecipeChallenge() {
        super.conpleted = false;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
