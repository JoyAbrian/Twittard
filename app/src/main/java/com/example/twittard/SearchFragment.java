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
    private ArrayList<Search> searches;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataSource dataSource = new DataSource();
        searches = dataSource.searches;
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
}