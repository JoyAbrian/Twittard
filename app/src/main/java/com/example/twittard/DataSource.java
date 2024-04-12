package com.example.twittard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DataSource {
    public static ArrayList<Account> accounts = generateDummyAccounts();
    public ArrayList<Search> searches = generateDummySearches();
    public ArrayList<Community> communities = generateDummyCommunities();
    public ArrayList<Tweet> tweets = generateDummyTweets();

    private static ArrayList<Account> generateDummyAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Folkshitt", "@folkshittmedia", R.drawable.account_folkshit_pp, R.drawable.account_folkshit_banner, "Media & Berita", "November 2023", "0", "227.664"));
        accounts.add(new Account("sosmed keras", "@sosmedkeras", R.drawable.account_sosmedkeras_pp, R.drawable.account_sosmedkeras_banner, "Media Personality", "September 2020", "6", "4.1M"));
        accounts.add(new Account("Kaka", "@KAKA", R.drawable.account_kaka_pp, R.drawable.account_kaka_banner, "Atlit", "Juli 2009", "234", "29.1M"));
        accounts.add(new Account("Elon Musk", "@elonmusk", R.drawable.account_elonmusk_pp, R.drawable.account_elonmusk_banner, "CEO", "Juni 2009", "573", "180.1M"));
        accounts.add(new Account("Dr. Bob Onder", "@BobOnderMO", R.drawable.account_bobonder_pp, R.drawable.account_bobonder_banner, "Politic", "Juli 2013", "2,444", "9,581"));
        return accounts;
    }

    private static ArrayList<Tweet> generateDummyTweets() {
        ArrayList<Tweet> tweets = new ArrayList<>();
        tweets.add(new Tweet(accounts.get(0), "2 jam", "ASTAGA", R.drawable.account_folkshit_post1, "91", "52", "358", "32rb"));
        tweets.add(new Tweet(accounts.get(0), "1 hari", "KENCENG BANGET TUH MOBIL", R.drawable.account_folkshit_post2, "352", "584", "11rb", "1,6jt"));
        tweets.add(new Tweet(accounts.get(0), "1 hari", "GEBER TEROS", R.drawable.account_folkshit_post3, "109", "68", "1,3rb", "143rb"));
        tweets.add(new Tweet(accounts.get(0), "2 hari", "DISELESAIKAN DEGAN TINJU", R.drawable.account_folkshit_post4, "1rb", "2,2rb", "23rb", "3,6jt"));
        tweets.add(new Tweet(accounts.get(1), "03 April", "Satu kata buat orang ini", R.drawable.account_sosmedkeras_post1, "6,6rb", "5,6rb", "43rb", "9,1jt"));
        tweets.add(new Tweet(accounts.get(1), "17 jam", "Lah kok", R.drawable.account_sosmedkeras_post2, "1,4rb", "3,6rb", "30rb", "2jt"));
        tweets.add(new Tweet(accounts.get(2), "06 Maret", "122 años de gloria", R.drawable.account_kaka_post1, "875", "13rb", "116rb", "1,3jt"));
        tweets.add(new Tweet(accounts.get(3), "1 hari", null, R.drawable.account_elonmusk_post1, "12rb", "47rb", "483rb", "59jt"));
        tweets.add(new Tweet(accounts.get(3), "11 April", "X algorithm update coming soon with more bangers and less clickbait!", null, "13rb", "19rb", "203rb", "61jt"));
        tweets.add(new Tweet(accounts.get(4), "9 jam", "A reminder for those who slept through 7th grade social studies: we have a Constitutional Republic, not a direct democracy.", null, "16", "14rb", "77", "2,4rb"));
        Collections.shuffle(tweets);
        return tweets;
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
