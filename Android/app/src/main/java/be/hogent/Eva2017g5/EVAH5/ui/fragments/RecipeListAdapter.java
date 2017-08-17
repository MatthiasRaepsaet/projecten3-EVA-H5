package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import be.hogent.Eva2017g5.EVAH5.ui.fragments.RecipesFragment;
import be.hogent.Eva2017g5.EVAH5.ui.fragments.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;


public class RecipeListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<RecipeRow> recipeRowList;
    private ArrayList<RecipeRow> originalList;


    public RecipeListAdapter(Context context, ArrayList<RecipeRow> originalList) {
        this.context = context;
        this.recipeRowList = new ArrayList<>(originalList);
        this.originalList = new ArrayList<>(originalList);
    }

    @Override
    public int getGroupCount() {
        return recipeRowList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return recipeRowList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        RecipeRow recipeRow = (RecipeRow) getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate(R.layout.parent_row, null);
        }

       // TextView heading = (TextView) convertView.findViewById(R.id.parent_text);
      //  heading.setText(recipeRow.getTitle().trim());
        //hier moet een onclicklistener komen om naar de pagina van het recept te gaan!
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void filterData(String query){
        query = query.toLowerCase();
        recipeRowList.clear();

        if(query.isEmpty()){
            recipeRowList.addAll(originalList);
        }
        else{
            //Constructing new list that matches query
            for (RecipeRow recipeRow : originalList){
                ArrayList<RecipeRow> newList = new ArrayList<RecipeRow>();
                if(recipeRow.getTitle().toLowerCase().contains(query)){
                    newList.add(recipeRow);
                }else if(recipeRow.getDescription().toLowerCase().contains(query)){
                    newList.add(recipeRow);
                }
            }
        }

        notifyDataSetChanged();
    }
}
