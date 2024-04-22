package com.example.twittard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFragment extends Fragment {
    private ArrayList<Search> searches;
    private ArrayList<Tweet> tweets;
    private LinearLayout parents, searchOutput, rvAccount, rvTweet;
    private ScrollView scrollView1, scrollView2;

    private ImageView header_search, toggleSetting;
    private EditText search_bar;
    private ProgressBar loading_bar;

    Executor executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.myLooper());

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
        rvAccount = rootView.findViewById(R.id.rvAccount);
        rvTweet = rootView.findViewById(R.id.rvTweet);

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
            searchOutput.setVisibility(View.GONE);
        }

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                searchOutput.setVisibility(View.GONE);
//                loadSearchOutput(search_bar.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchOutput.setVisibility(View.GONE);
                loadSearchOutput(search_bar.getText().toString());
            }
        });

        return rootView;
    }

    private void loadSearchOutput(String searchInput) {
        loading_bar.setVisibility(View.VISIBLE);
        Pattern pattern = Pattern.compile(Pattern.quote(searchInput), Pattern.CASE_INSENSITIVE);

        executor.execute(() -> {
            ArrayList<Tweet> tweetSearchOutput = new ArrayList<>();
            ArrayList<Account> accountSearchOutput = new ArrayList<>();
            DataSource dataSource = new DataSource();
            tweets = dataSource.tweets;

            int tweet_size = tweets.size();
            for (int i = 0; i < tweet_size; i++) {
                if (tweets.get(i).getTweet() != null) {
                    Matcher matcherTweet = pattern.matcher(tweets.get(i).getTweet());
                    if (matcherTweet.find()) {
                        tweetSearchOutput.add(tweets.get(i));
                    }
                }

                Matcher matcherAccountFullname = pattern.matcher(tweets.get(i).getAccount().getFullname());
                Matcher matcherAccountUsername = pattern.matcher(tweets.get(i).getAccount().getUsername());

                if (matcherAccountUsername.find() || matcherAccountFullname.find()) {
                    accountSearchOutput.add(tweets.get(i).getAccount());
                }
            }

            HashSet<Account> uniqueAccounts = new HashSet<>();
            for (Account account : accountSearchOutput) {
                uniqueAccounts.add(account);
            }
            accountSearchOutput.clear();
            accountSearchOutput.addAll(uniqueAccounts);

            ArrayList<Account> finalAccountSearchOutput = accountSearchOutput;
            ArrayList<Tweet> finalTweetSearchOutput = tweetSearchOutput;

            handler.post(() -> {
                rvAccount.removeAllViews();
                rvTweet.removeAllViews();

                for (Account account : finalAccountSearchOutput) {
                    rvAccount.addView(inflateTemplateAccount(account));
                }

                for (Tweet tweet : finalTweetSearchOutput) {
                    rvTweet.addView(inflateTemplateTweet(tweet));
                }

                searchOutput.setVisibility(View.VISIBLE);
                loading_bar.setVisibility(View.GONE);
            });
        });
    }

    private View inflateTemplateAccount(Account account) {
        View accountView = LayoutInflater.from(getContext()).inflate(R.layout.template_search_account, null);

        CircleImageView profilePhoto = accountView.findViewById(R.id.profilePhoto);
        TextView profileFullname = accountView.findViewById(R.id.profileFullname);
        TextView profileUsername = accountView.findViewById(R.id.profileUsername);
        TextView profileType = accountView.findViewById(R.id.profileType);

        profilePhoto.setImageResource(account.getProfilePhoto());
        profileFullname.setText(account.getFullname());
        profileUsername.setText(account.getUsername());
        profileType.setText(account.getBirthdate());

        accountView.setOnClickListener(v -> {
            openProfile(account);
        });

        return accountView;
    }

    private View inflateTemplateTweet(Tweet tweet) {
        View tweetView = LayoutInflater.from(getContext()).inflate(R.layout.template_tweet, null);

        CircleImageView accountPicture = tweetView.findViewById(R.id.accountPicture);
        TextView accountFullname = tweetView.findViewById(R.id.accountFullname);
        TextView accountUsername = tweetView.findViewById(R.id.accountUsername);
        TextView tweetDate = tweetView.findViewById(R.id.tweetDate);
        TextView tweetComments = tweetView.findViewById(R.id.tweetComments);
        TextView tweetReposts = tweetView.findViewById(R.id.tweetReposts);
        TextView tweetLikes = tweetView.findViewById(R.id.tweetLikes);
        TextView tweetViews = tweetView.findViewById(R.id.tweetViews);
        ImageView tweetDrawablePicture = tweetView.findViewById(R.id.tweetDrawablePicture);
        ImageView tweetUriImage = tweetView.findViewById(R.id.tweetUriImage);
        TextView tweetText = tweetView.findViewById(R.id.tweetText);
        ImageView toggleDelete = tweetView.findViewById(R.id.toggleDelete);

        toggleDelete.setVisibility(View.GONE);

        accountPicture.setImageResource(tweet.getAccount().getProfilePhoto());
        accountFullname.setText(tweet.getAccount().getFullname());
        accountUsername.setText(tweet.getAccount().getUsername());
        tweetDate.setText(tweet.getDate());
        tweetComments.setText(tweet.getComment());
        tweetReposts.setText(tweet.getRepost());
        tweetLikes.setText(tweet.getLike());
        tweetViews.setText(tweet.getView());

        if (tweet.getTweet() == null) {
            tweetText.setVisibility(View.GONE);
        } else {
            tweetText.setVisibility(View.VISIBLE);
            tweetText.setText(tweet.getTweet());
        }

        if (tweet.getImage() == 1 && tweet.getUriImage() == null) {
            tweetUriImage.setVisibility(View.GONE);
            tweetDrawablePicture.setVisibility(View.GONE);
        } else if (tweet.getImage() != 1 && tweet.getUriImage() == null) {
            tweetDrawablePicture.setVisibility(View.VISIBLE);
            tweetUriImage.setVisibility(View.GONE);
            tweetDrawablePicture.setImageResource(tweet.getImage());
        } else if (tweet.getImage() == 1 && tweet.getUriImage() != null) {
            tweetDrawablePicture.setVisibility(View.GONE);
            tweetUriImage.setVisibility(View.VISIBLE);
            tweetUriImage.setImageURI(tweet.getUriImage());
        }

        accountPicture.setOnClickListener(v -> {
            openProfile(tweet.getAccount());
        });

        accountFullname.setOnClickListener(v -> {
            openProfile(tweet.getAccount());
        });

        accountUsername.setOnClickListener(v -> {
            openProfile(tweet.getAccount());
        });

        return tweetView;
    }

    private void openProfile(Account account) {
        Intent intent = new Intent(requireContext(), ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_ACCOUNT, account);
        requireContext().startActivity(intent);
    }
}