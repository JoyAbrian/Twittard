package com.example.twittard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PostingFragment extends Fragment {

    public PostingFragment() {}

    public static PostingFragment newInstance() {
        return new PostingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posting, container, false);
    }
}
