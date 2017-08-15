package be.hogent.Eva2017g5.EVAH5.ui;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import be.hogent.Eva2017g5.EVAH5.domain.Challenge;
import be.hogent.Eva2017g5.EVAH5.domain.VeganChallenge;
import be.hogent.Eva2017g5.EVAH5.ui.fragments.QuizPickerFragment;
import be.hogent.Eva2017g5.EVAH5.ui.fragments.RecipePickerFragment;
import be.hogent.Eva2017g5.EVAH5.ui.fragments.RestaurantPickerFragment;
import be.hogent.Eva2017g5.R;

public class ChallengePickerActivity extends AppCompatActivity implements QuizPickerFragment.OnFragmentInteractionListener, RecipePickerFragment.OnFragmentInteractionListener, RestaurantPickerFragment.OnFragmentInteractionListener{

    //fields
    VeganChallenge veganChallenge;

    //custom methods
    public void persistVeganChallenge(){
        // save veganChallenge in sharedpref
        SharedPreferences sharedPref = getSharedPreferences("veganChallenge", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("startDate", veganChallenge.getStartDate().getTime());
        editor.putInt("recipesMade", veganChallenge.getRecipesMade());
        editor.putInt("restaurantsVisited", veganChallenge.getRestaurantsVisited());
        editor.putInt("quizzesTaken", veganChallenge.getQuizzesTaken());

        //save list of challenges in sharedpref
        Gson gson = new Gson();
        String challengesJsonString = gson.toJson(veganChallenge.getChallengesList());
        editor.putString("challengesList", challengesJsonString);
        editor.commit();
    }

    public void recallVeganChallenge(){
        SharedPreferences sharedPref = getSharedPreferences("veganChallenge",MODE_PRIVATE );
//        veganChallenge.setStartDate(new Date(sharedPref.getLong("startDate", Calendar.getInstance().getTimeInMillis())));
        veganChallenge.setStartDate(new Date(1502301412601L));
        veganChallenge.setRecipesMade(sharedPref.getInt("recipesMade",0));
        veganChallenge.setRestaurantsVisited(sharedPref.getInt("restaurantsVisited",0));
        veganChallenge.setQuizzesTaken(sharedPref.getInt("quizzesTaken",0));
        //custom types
        Gson gson = new Gson();
        veganChallenge.setCurrentChallenge(gson.fromJson(sharedPref.getString("currentChallenge",""),Challenge.class));
        //compile challengeslist from json form sharedpred
        Type type = new TypeToken<List<Challenge>>(){}.getType();
        List<Challenge> challengesList= gson.fromJson(sharedPref.getString("challengesJsonString",""), type);
        veganChallenge.setChallengesList(challengesList);
    }

    //lifecylce methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_picker);
        veganChallenge = new VeganChallenge();
        //load veganChallenge from sharedpref
        recallVeganChallenge();

        //acitonlisteners for 3 toggle buttons
        final ToggleButton btnRecipes = (ToggleButton) findViewById(R.id.recipeToggleButton);
        final ToggleButton btnRestaurant = (ToggleButton) findViewById(R.id.restaurantToggleButton);
        final ToggleButton btnQuiz = (ToggleButton) findViewById(R.id.quizToggleButton);

        btnRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set other button states to inactive
                btnRecipes.setChecked(true);
                btnRestaurant.setChecked(false);
                btnQuiz.setChecked(false);

                //load RestaurantFragment
                FragmentManager fm = getSupportFragmentManager();
                RestaurantPickerFragment fragment = (RestaurantPickerFragment) fm.findFragmentByTag("RESTAURANTFRAGMENT");
                if(fragment == null){
                    fragment = new RestaurantPickerFragment();
                    fm.beginTransaction().replace(R.id.challengeHolder, fragment, "RESTAURANTFRAGMENT").commit();
                }
            }
        });
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set other button states to inactive
                btnRestaurant.setChecked(true);
                btnRecipes.setChecked(false);
                btnQuiz.setChecked(false);

                //load RecipeFragment
                FragmentManager fm = getSupportFragmentManager();
                RecipePickerFragment fragment = (RecipePickerFragment) fm.findFragmentByTag("RECIPEFRAGMENT");
                if(fragment == null){
                    fragment = new RecipePickerFragment();
                    fm.beginTransaction().replace(R.id.challengeHolder, fragment, "RECIPEFRAGMENT").commit();
                }
            }
        });
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set other button states to inactive
                btnQuiz.setChecked(true);
                btnRecipes.setChecked(false);
                btnRestaurant.setChecked(false);

                //load Quizfragment
                FragmentManager fm = getSupportFragmentManager();
                QuizPickerFragment fragment = (QuizPickerFragment) fm.findFragmentByTag("QUIZFRAGMENT");
                if(fragment == null){
                    fragment = new QuizPickerFragment();
                    fm.beginTransaction().replace(R.id.challengeHolder, fragment, "QUIZFRAGMENT").commit();
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        //update text
        TextView daysTV = (TextView) findViewById(R.id.dayCounterTextView);
        daysTV.setText(String.format(getString(R.string.currentDay),veganChallenge.getDaysSinceStart()));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
