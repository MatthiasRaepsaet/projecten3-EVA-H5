package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import be.hogent.Eva2017g5.EVAH5.domain.VeganChallenge;
import be.hogent.Eva2017g5.EVAH5.ui.ChallengePickerActivity;
import be.hogent.Eva2017g5.R;

public class LaunchChallengePickerFragment extends Fragment {

    private VeganChallenge veganChallenge;

    public void setVeganChallenge(VeganChallenge veganChallenge) {
        this.veganChallenge = veganChallenge;
    }
    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    public LaunchChallengePickerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LaunchChallengePickerFragment newInstance(String param1, String param2) {
        LaunchChallengePickerFragment fragment = new LaunchChallengePickerFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //onclicklistener for launching challengepicker




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_challenge_summary, container, false);



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final Button btn = (Button) getView().findViewById(R.id.challengePickerLauncherButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChallengePickerActivity.class);
                startActivity(intent);
            }
        });

        TextView daysTV = (TextView) getView().findViewById(R.id.currentDayTextView);
        daysTV.setText(String.format(getString(R.string.currentDay), veganChallenge.getDaysSinceStart()));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
