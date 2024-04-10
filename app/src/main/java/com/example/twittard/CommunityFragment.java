package com.example.twittard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CommunityFragment extends Fragment {

    public CommunityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    private ArrayList<Community> generateDummyCommunities() {
        ArrayList<Community> communities = new ArrayList<>();
        communities.add(new Community(R.drawable.communitydesignsphere, "The Design Sphere", "244rb", "Desain"));
        communities.add(new Community(R.drawable.communityxfeedback, "X Communities Feedback", "33rb", ""));
        communities.add(new Community(R.drawable.communityapple, "Apple", "148rb", "Teknologi"));
        communities.add(new Community(R.drawable.communityartistonthechain, "Artists On The Chain", "111rb", "Seni"));
        communities.add(new Community(, "Software Engineering", "25rb", "Perangkat Lunak"));
        communities.add(new Community(, "Memes", "131rb", "Seru-Seruan"));
        communities.add(new Community(, "Yankees Twitter", "14rb", "Bisbol"));
        communities.add(new Community(, "Diablo IV", "17rb", "Game"));
        communities.add(new Community(, "Dogs", "94rb", "Hewan"));
        communities.add(new Community(, "DC", "21rb", "Komik"));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        communities.add(new Community(, "", "", ""));
        return communities;
    }
}
