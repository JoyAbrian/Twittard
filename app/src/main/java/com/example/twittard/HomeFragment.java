package com.example.twittard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rvTweets = rootView.findViewById(R.id.parent);

        if (rvTweets != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvTweets.setLayoutManager(layoutManager);

            TweetAdapter adapter = new TweetAdapter(DataSource.tweets);
            rvTweets.setAdapter(adapter);
        }

        return rootView;
    }
}