package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.hogent.Eva2017g5.EVAH5.rest.ApiInterface;
import be.hogent.Eva2017g5.EVAH5.rest.Challenge;
import be.hogent.Eva2017g5.EVAH5.rest.Login;
import be.hogent.Eva2017g5.EVAH5.rest.RetrofitAPI;
import be.hogent.Eva2017g5.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengesFragment extends Fragment {
    private View v;
   private Challenge randomChallenge;
    private Button startChallenge;
    private Button nieuweChallenge;
    private TextView challengeTitel, challengeDifficulty, challengeDescription, challengePoints;
    List<Challenge> challengeList = new ArrayList<>();


    public ChallengesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.v= inflater.inflate(R.layout.fragment_challenges, container, false);
     //   challengeDescription = (TextView) v.findViewById(R.id.challengeDescription);
    //    challengeDifficulty = (TextView) v.findViewById(R.id.challengeDifficulty);
    //    challengeTitel = (TextView) v.findViewById(R.id.challengeTitel);
   //     challengePoints = (TextView) v.findViewById(R.id.challengePoints);
    //    startChallenge = (Button) v.findViewById(R.id.startChallenge);
   //     nieuweChallenge = (Button) v.findViewById(R.id.nieuweChallenge);
        getRandomChallenge();
        return v;
    }

    public void getRandomChallenge() {

        ApiInterface mApiService = RetrofitAPI.getDefaultInterfaceService();
        Call<List<Challenge>> mService = mApiService.getChallenges();

        mService.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                challengeList = response.body();
                Random random = new Random();
                int i = random.nextInt(challengeList.size());
                randomChallenge = challengeList.get(i);
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {

            }
        });
    }


}
