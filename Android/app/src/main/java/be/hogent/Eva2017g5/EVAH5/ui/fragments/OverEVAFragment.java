package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.hogent.Eva2017g5.R;


public class OverEVAFragment extends Fragment {

    private TextView intro, geschiedenistitel, geschiedenis, aanpaktitel, aanpak;

    public OverEVAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_over_eva, container, false);
        intro = (TextView) v.findViewById(R.id.intro);
        geschiedenistitel = (TextView) v.findViewById(R.id.geschiedenistitel);
        geschiedenis = (TextView) v.findViewById(R.id.geschiedenis);
        aanpaktitel = (TextView) v.findViewById(R.id.aanpaktitel);
        aanpak = (TextView) v.findViewById(R.id.aanpak);
        return v;
    }


}
