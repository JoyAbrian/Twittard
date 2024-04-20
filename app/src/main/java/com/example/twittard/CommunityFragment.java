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
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
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
}
