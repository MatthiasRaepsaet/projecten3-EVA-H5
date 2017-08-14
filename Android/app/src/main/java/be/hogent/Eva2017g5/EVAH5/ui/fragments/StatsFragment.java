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


public class StatsFragment extends Fragment {
    private VeganChallenge veganChallenge;

    public void setVeganChallenge(VeganChallenge veganChallenge) {
        this.veganChallenge = veganChallenge;
    }

    public StatsFragment() {
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
        TextView carbon = (TextView) getView().findViewById(R.id.co2TextView);
        TextView water = (TextView) getView().findViewById(R.id.waterTextView);
        TextView forest = (TextView) getView().findViewById(R.id.forestTextView);
        carbon.setText(String.format(getString(R.string.co2saved), veganChallenge.getCarbonReduced()));
        water.setText(String.format(getString(R.string.waterSaved), veganChallenge.getWaterReduced()));
        forest.setText(String.format(getString(R.string.forestSaved), veganChallenge.getForestSaved()));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
