package com.example.twittard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    ArrayList<Search> searches;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searches = generateDummySearches();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        LinearLayout parents = rootView.findViewById(R.id.parents);

        int searches_size = searches.size();
        for (int i = 0; i < searches_size; i++) {
            View searchView = LayoutInflater.from(getContext()).inflate(R.layout.template_search, null);
            TextView searchType = searchView.findViewById(R.id.searchType);
            TextView searchName = searchView.findViewById(R.id.searchName);
            TextView searchPosts = searchView.findViewById(R.id.searchPosts);

            searchType.setText(searches.get(i).getType());
            searchName.setText(searches.get(i).getName());
            searchPosts.setText(searches.get(i).getPosts());

            parents.addView(searchView);
        }

        return rootView;
    }

    private static ArrayList<Search> generateDummySearches() {
        ArrayList<Search> searches = new ArrayList<>();
        searches.add(new Search("Sedang tren dalam topik Indonesia", "Innalillahi", "1.693"));
        searches.add(new Search("Politik • Populer", "Ridwan Kamil", "1.238"));
        searches.add(new Search("Sedang tren dalam topik Indonesia", "BEM UI", "15,1 rb"));
        searches.add(new Search("Sedang tren dalam topik Indonesia", "Jawa", "15,4 rb"));
        searches.add(new Search("Hiburan • Populer", "Cinta Laura", "1.738 rb"));
        searches.add(new Search("Sedang tren dalam topik Indonesia", "Papua", "30,4 rb"));
        searches.add(new Search("Sedang tren dalam topik Indonesia", "Apel Siaga 3", "2.030"));
        searches.add(new Search("Sedang tren dalam topik Indonesia", "Labrak", "2.052"));
        return searches;
    }
}