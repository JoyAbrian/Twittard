package com.example.twittard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommunityFragment extends Fragment {
    private ArrayList<Community> communities;

    private LinearLayout parents, parentsSearch, searchOutputs;
    private TextView header_title;
    private ImageView toggleSearch, toggleAddCommunity;
    private EditText search_bar;
    private ProgressBar loading_bar;
    private ScrollView scrollView1, scrollView2;

    Executor executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

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

        parents = rootView.findViewById(R.id.parents);
        parentsSearch = rootView.findViewById(R.id.parentsSearch);
        header_title = rootView.findViewById(R.id.header_title);
        toggleSearch = rootView.findViewById(R.id.toggleSearch);
        toggleAddCommunity = rootView.findViewById(R.id.toggleAddCommunity);
        search_bar = rootView.findViewById(R.id.search_bar);
        loading_bar = rootView.findViewById(R.id.loading_bar);
        searchOutputs = rootView.findViewById(R.id.searchOutputs);
        scrollView1 = rootView.findViewById(R.id.scrollView1);
        scrollView2 = rootView.findViewById(R.id.scrollView2);

        int communities_size = communities.size();
        for (int i = 0; i < communities_size; i++) {
            parents.addView(inflateTemplate(communities.get(i)));
        }

        toggleSearch.setOnClickListener(v -> {
            header_title.setVisibility(View.GONE);
            toggleSearch.setVisibility(View.GONE);
            toggleAddCommunity.setVisibility(View.GONE);
            scrollView1.setVisibility(View.GONE);

            search_bar.setVisibility(View.VISIBLE);
            scrollView2.setVisibility(View.VISIBLE);

            for (int i = 0; i < communities_size; i++) {
                searchOutputs.addView(inflateTemplate(communities.get(i)));
                loading_bar.setVisibility(View.GONE);
                searchOutputs.setVisibility(View.VISIBLE);
            }
        });

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchOutputs.setVisibility(View.GONE);
                loadSearchOutput(search_bar.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed
            }
        });

        return rootView;
    }

    private void loadSearchOutput(String searchInput) {
        loading_bar.setVisibility(View.VISIBLE);
        Pattern pattern = Pattern.compile(Pattern.quote(searchInput), Pattern.CASE_INSENSITIVE);

        executor.execute(() -> {
            ArrayList<Community> communitySearchOutput = new ArrayList<>();
            DataSource dataSource = new DataSource();
            communities = dataSource.communities;

            int communities_size = communities.size();
            for (int i = 0; i < communities_size; i++) {
                Matcher matcherName = pattern.matcher(communities.get(i).getName());
                Matcher matcherType = pattern.matcher(communities.get(i).getType());
                if (matcherName.find() || matcherType.find()) {
                    communitySearchOutput.add(communities.get(i));
                }
            }

            handler.post(() -> {
                searchOutputs.removeAllViews();

                for (Community community : communitySearchOutput) {
                    searchOutputs.addView(inflateTemplate(community));
                }

                loading_bar.setVisibility(View.GONE);
                searchOutputs.setVisibility(View.VISIBLE);
            });
        });
    }

    private View inflateTemplate(Community community) {
        View communityView = LayoutInflater.from(getContext()).inflate(R.layout.template_community, null);
        ImageView communityPicture = communityView.findViewById(R.id.communityPicture);
        TextView communityName = communityView.findViewById(R.id.communityName);
        TextView communityMembers = communityView.findViewById(R.id.communityMembers);
        TextView communityType = communityView.findViewById(R.id.communityType);

        communityPicture.setImageResource(community.getImage());
        communityName.setText(community.getName());
        communityMembers.setText(community.getMembers());
        communityType.setText(community.getType());

        return communityView;
    }
}
