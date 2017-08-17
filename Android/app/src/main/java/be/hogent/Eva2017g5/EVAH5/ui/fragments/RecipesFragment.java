package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import be.hogent.Eva2017g5.EVAH5.ui.MainActivity;
import be.hogent.Eva2017g5.R;
import java.util.ArrayList;
import java.util.List;

import be.hogent.Eva2017g5.EVAH5.rest.ApiInterface;
import be.hogent.Eva2017g5.EVAH5.rest.Login;
import be.hogent.Eva2017g5.EVAH5.rest.Recipe;
import be.hogent.Eva2017g5.EVAH5.rest.RetrofitAPI;
import be.hogent.Eva2017g5.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipesFragment extends Fragment {

    private static final String TAG = "RecipesFragment";

    private View v;
    private LinearLayout llContainer;
    private EditText rSearch;
    private ListView lvRecipes;
    private ArrayList<Recipe> recipeArrayList= new ArrayList<Recipe>();
    private RecipeAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipesFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.v= inflater.inflate(R.layout.fragment_recipes, container, false);
        rSearch = (EditText) v.findViewById(R.id.rSearch) ;
        lvRecipes = (ListView) v.findViewById(R.id.lvRecipes) ;


        rSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        ApiInterface mApiService = RetrofitAPI.getDefaultInterfaceService();
        Call<List<Recipe>>  mService = mApiService.getRecipes();

        mService.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipeArrayList = (ArrayList<Recipe>) response.body();
                adapter = new RecipeAdapter(getActivity(), recipeArrayList);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });

    }
}
