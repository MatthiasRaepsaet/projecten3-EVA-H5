package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.hogent.Eva2017g5.EVAH5.domain.VeganChallenge;
import be.hogent.Eva2017g5.R;


public class QuizChallengeFragment extends Fragment {
    private VeganChallenge veganChallenge;

    public void setVeganChallenge(VeganChallenge veganChallenge) {
        this.veganChallenge = veganChallenge;
    }

    public QuizChallengeFragment() {
        // Required empty public constructor
    }


    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
