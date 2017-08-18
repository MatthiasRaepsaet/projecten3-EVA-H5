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


public class VegvsVegFragment extends Fragment {
    private TextView vegetarismetitel, vegetarisme, veganismetitel, veganisme;

    public VegvsVegFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vegvs_veg, container, false);
        vegetarismetitel = (TextView) v.findViewById(R.id.vegetarismetitel);
        vegetarisme = (TextView) v.findViewById(R.id.vegetarisme);
        veganismetitel = (TextView) v.findViewById(R.id.veganismetitel);
        veganisme = (TextView) v.findViewById(R.id.veganisme);
        return v;
    }
}
