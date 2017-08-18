package be.hogent.Eva2017g5.EVAH5.ui.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import be.hogent.Eva2017g5.R;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.Recipe;

/**
 * Created by sofie.
 */

class RecipeAdapter extends BaseAdapter implements Filterable {
    private ArrayList<Recipe> rOriginal, rDisplayed;
    LayoutInflater inflater;
    private ListView rListView;

    public RecipeAdapter(Context context, ArrayList<Recipe> recipeArrayList){
        this.rDisplayed = recipeArrayList;
        this.rOriginal = recipeArrayList;
        inflater= LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return rDisplayed.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        LinearLayout llContainer;
        TextView rTitel,rDescription, rVotes;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row, null);
            holder.llContainer = (LinearLayout)convertView.findViewById(R.id.llContainer);
            holder.rTitel = (TextView) convertView.findViewById(R.id.rTitel);
            holder.rDescription = (TextView) convertView.findViewById(R.id.rDescription);
            holder.rVotes = (TextView) convertView.findViewById(R.id.rVotes);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.rTitel.setText(rDisplayed.get(position).getTitle());
        holder.rDescription.setText(rDisplayed.get(position).getDescription());
        holder.rVotes.setText(rDisplayed.get(position).getVotes());



        return convertView;
    }



    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                rDisplayed = (ArrayList<Recipe>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Recipe> FilteredArrList = new ArrayList<Recipe>();

                if (rOriginal == null) {
                    rOriginal = new ArrayList<Recipe>(rDisplayed); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = rOriginal.size();
                    results.values = rOriginal;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < rOriginal.size(); i++) {
                        String data = rOriginal.get(i).getTitle();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new Recipe(rOriginal.get(i).getTitle(),rOriginal.get(i).getDescription(), rOriginal.get(i).getVotes()));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}

