package com.example.twittard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CommunityFragment extends Fragment {
    private ArrayList<Community> communities;

    public CommunityFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataSource dataSource = new DataSource();
        communities = dataSource.communities;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        LinearLayout parents = rootView.findViewById(R.id.parents);

        int communities_size = communities.size();
        for (int i = 0; i < communities_size; i++) {
            View communityView = LayoutInflater.from(getContext()).inflate(R.layout.template_community, null);
            ImageView communityPicture = communityView.findViewById(R.id.communityPicture);
            TextView communityName = communityView.findViewById(R.id.communityName);
            TextView communityMembers = communityView.findViewById(R.id.communityMembers);
            TextView communityType = communityView.findViewById(R.id.communityType);

            communityPicture.setImageResource(communities.get(i).getImage());
            communityName.setText(communities.get(i).getName());
            communityMembers.setText(communities.get(i).getMembers());
            communityType.setText(communities.get(i).getType());

            parents.addView(communityView);
        }

            return rootView;
    }

    private ArrayList<Community> generateDummyCommunities() {
        ArrayList<Community> communities = new ArrayList<>();
        communities.add(new Community(R.drawable.communitydesignsphere, "The Design Sphere", "244rb", "Desain"));
        communities.add(new Community(R.drawable.communityxfeedback, "X Communities Feedback", "33rb", ""));
        communities.add(new Community(R.drawable.communityapple, "Apple", "148rb", "Teknologi"));
        communities.add(new Community(R.drawable.communityartistonthechain, "Artists On The Chain", "111rb", "Seni"));
        communities.add(new Community(R.drawable.communitysoftwareengineering, "Software Engineering", "25rb", "Perangkat Lunak"));
        communities.add(new Community(R.drawable.communitymemes, "Memes", "131rb", "Seru-Seruan"));
        communities.add(new Community(R.drawable.communityyankeestwitter, "Yankees Twitter", "14rb", "Bisbol"));
        communities.add(new Community(R.drawable.communitydiabloiv, "Diablo IV", "17rb", "Game"));
        communities.add(new Community(R.drawable.communitydogs, "Dogs", "94rb", "Hewan"));
        communities.add(new Community(R.drawable.communitydc, "DC", "21rb", "Komik"));
        return communities;
    }
}
