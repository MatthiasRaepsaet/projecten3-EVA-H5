package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.hogent.Eva2017g5.EVAH5.domainAndModel.ApiInterface;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.Challenge;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.ChallengeUserDto;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.EvaUser;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.RetrofitAPI;
import be.hogent.Eva2017g5.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengesFragment extends Fragment {
    private View v;
   private Challenge randomChallenge;
    private Button startChallenge,nieuweChallenge, voltooiChallenge;
    private TextView challengeTitel, challengeDescription, uitleg;
    List<Challenge> challengeList = new ArrayList<>();
    boolean flag;
    EvaUser user;


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
        flag=true;
        // Inflate the layout for this fragment
        this.v= inflater.inflate(R.layout.fragment_challenges, container, false);

           challengeDescription = (TextView) v.findViewById(R.id.challengeDescription);

            challengeTitel = (TextView) v.findViewById(R.id.challengeTitel);
         uitleg = (TextView) v.findViewById(R.id.uitleg);
           startChallenge = (Button) v.findViewById(R.id.startChallenge);
             nieuweChallenge = (Button) v.findViewById(R.id.nieuweChallenge);
            voltooiChallenge = (Button) v.findViewById(R.id.voltooiChallenge);

        String userId = getActivity().getIntent().getExtras().getString("USERID");
        ApiInterface mApiService = RetrofitAPI.getDefaultInterfaceService();
        Call<EvaUser> mService = mApiService.getUser(userId);
        mService.enqueue(new Callback<EvaUser>() {
            @Override
            public void onResponse(Call<EvaUser> call, Response<EvaUser> response) {
                user = response.body();
                if (user.getTodaysChallenge().get(0)!= null){
                    flag = false;
                }
            }

            @Override
            public void onFailure(Call<EvaUser> call, Throwable t) {

            }
        });


        LinearLayout zonder= (LinearLayout) v.findViewById(R.id.zonderChallenge);
        LinearLayout met= (LinearLayout) v.findViewById(R.id.metChallenge);
        if(flag){
            met.setVisibility(View.GONE);
            zonder.setVisibility(View.VISIBLE);
        }else{
            zonder.setVisibility(View.GONE);
            met.setVisibility(View.VISIBLE);
        }

        startChallenge.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        getRandomChallenge();
       }         });

        nieuweChallenge.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                getRandomChallenge();
            }         });

        voltooiChallenge.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                ApiInterface mApiService = RetrofitAPI.getDefaultInterfaceService();
                ChallengeUserDto challengeUserDto2 = new ChallengeUserDto(user.getTodaysChallenge().get(0).getId()+"", user.getId()+"");
                Call<ChallengeUserDto> service = mApiService.completeChallenge(challengeUserDto2);
                service.enqueue(new Callback<ChallengeUserDto>() {
                    @Override
                    public void onResponse(Call<ChallengeUserDto> call, Response<ChallengeUserDto> response) {

                    }

                    @Override
                    public void onFailure(Call<ChallengeUserDto> call, Throwable t) {

                    }
                });


                Call<List<Challenge>> mService = mApiService.getChallenges();

                mService.enqueue(new Callback<List<Challenge>>() {
                    @Override
                    public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {

                        getRandomChallenge();
                    }

                    @Override
                    public void onFailure(Call<List<Challenge>> call, Throwable t) {

                    }
                });
            }         });

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
                ApiInterface mApiService = RetrofitAPI.getDefaultInterfaceService();
                ChallengeUserDto challengeUserDto= new ChallengeUserDto(randomChallenge.getId()+"", user.getId()+"");
                Call<ChallengeUserDto> service = mApiService.selectChallenge(challengeUserDto);
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {

            }
        });

    }


}
