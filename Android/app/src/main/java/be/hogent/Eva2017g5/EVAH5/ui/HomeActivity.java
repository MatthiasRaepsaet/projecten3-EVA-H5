package be.hogent.Eva2017g5.EVAH5.ui;



import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import be.hogent.Eva2017g5.EVAH5.domain.Challenge;
import be.hogent.Eva2017g5.EVAH5.domain.VeganChallenge;
import be.hogent.Eva2017g5.EVAH5.ui.fragments.ChallengeSummaryFragment;
import be.hogent.Eva2017g5.EVAH5.ui.fragments.StatsFragment;
import be.hogent.Eva2017g5.R;

public class HomeActivity extends AppCompatActivity implements ChallengeSummaryFragment.OnFragmentInteractionListener{


    VeganChallenge veganChallenge;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //create new veganChallenge, populate if found in sharedpreference
        veganChallenge = new VeganChallenge();

        this.recallVeganChallenge();

        // start fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //add Challenge Summary fragment
        final ChallengeSummaryFragment challengeSummaryFrag = new ChallengeSummaryFragment();
        challengeSummaryFrag.setVeganChallenge(veganChallenge);
        fragmentTransaction.add(R.id.challengeSummaryHolder, challengeSummaryFrag);

        //add StatsFragment
        final StatsFragment statsFragment = new StatsFragment();
        statsFragment.setVeganChallenge(veganChallenge);
        fragmentTransaction.add(R.id.statsHolder, statsFragment);

        //commit fragment additions
        fragmentTransaction.commit();

        //construct alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.restartChallenge);
        builder.setMessage(R.string.confirmRestartChallengeMessage);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                //discard veganChallenge object, create new one. persist to sharedPreferences
                veganChallenge = new VeganChallenge();
                HomeActivity.this.persistVeganChallenge();
                HomeActivity.this.onResume();
                statsFragment.setVeganChallenge(veganChallenge);
                statsFragment.onResume();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        final AlertDialog restartDialog = builder.create();

        //make restart clickable and add action listener
        TextView restartTV= (TextView) findViewById(R.id.restartTextView);
        restartTV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                restartDialog.show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //update day counter

        TextView dayCounter = (TextView) findViewById(R.id.daysTextView);
        dayCounter.setText(String.format("%d",veganChallenge.getDaysSinceStart()));
//        dayCounter.setText("6");

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.persistVeganChallenge();

    }

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
}
