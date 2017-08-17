package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import java.util.ArrayList;

import be.hogent.Eva2017g5.EVAH5.rest.Recipe;
import be.hogent.Eva2017g5.R;


public class RecipesFragment extends Fragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private View v;
    private ExpandableListView recipeList;
    private ArrayList<Recipe> recipes;
    private android.widget.SearchView search;
    private RecipeListAdapter listAdapter;
    private SearchManager searchManager;
    private ArrayList<RecipeRow> parentList = new ArrayList<>();
    private ArrayList<RecipeRow> showTheseParentList = new ArrayList<>();
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
        this.v= inflater.inflate(R.layout.fragment_recipes_list, container, false);
      //  recipeList = (ExpandableListView) v.findViewById(R.id.listExpandableRecipes);
        searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
      //  search = (SearchView) v.findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        parentList = new ArrayList<>();
        showTheseParentList = new ArrayList<>();

        this.recipes = getArguments().getParcelableArrayList("RECIPE_TITLES");
        displayList();
        expandAll();

        return v;
    }


    public void expandAll(){
        for(int i =0; i<listAdapter.getGroupCount();i++){
            recipeList.expandGroup(i);
        }
    }

    public void displayList(){
        loadData();
        listAdapter = new RecipeListAdapter(getContext(), parentList);
        recipeList.setAdapter(listAdapter);
    }

    public void loadData(){
        ArrayList<RecipeRow> defaultChildList = new ArrayList<>();
        //hier nog logica toevoegen

        System.out.println("Finished loading data: " + parentList.toString());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }
}
