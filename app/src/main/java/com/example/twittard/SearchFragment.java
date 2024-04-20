package com.example.twittard;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private ArrayList<Search> searches;
    private LinearLayout parents, searchOutput;
    private NestedScrollView scrollView1;
    private ScrollView scrollView2;

    private ImageView header_search, toggleSetting;
    private EditText search_bar;
    private ProgressBar loading_bar;

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

        parents = rootView.findViewById(R.id.parents);
        scrollView1 = rootView.findViewById(R.id.scrollView1);
        scrollView2 = rootView.findViewById(R.id.scrollView2);
        header_search = rootView.findViewById(R.id.header_search);
        toggleSetting = rootView.findViewById(R.id.toggleSetting);
        search_bar = rootView.findViewById(R.id.search_bar);
        loading_bar = rootView.findViewById(R.id.loading_bar);
        searchOutput = rootView.findViewById(R.id.searchOutput);

        header_search.setOnClickListener(v -> {
            header_search.setVisibility(View.GONE);
            toggleSetting.setVisibility(View.GONE);
            scrollView2.setVisibility(View.GONE);

            search_bar.setVisibility(View.VISIBLE);
            scrollView1.setVisibility(View.VISIBLE);
        });

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