package com.example.nbatracker;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SharedViewModel extends AndroidViewModel {

    private MutableLiveData<JSONObject> links;
    private MutableLiveData<ArrayList<Team>> teams;
    private MutableLiveData<ArrayList<Record>> standingsEast;
    private MutableLiveData<ArrayList<Record>> standingsWest;
    private MutableLiveData<ArrayList<Article>> articles;
    private MutableLiveData<ArrayList<Game>> games;

    String endpoint;

    public SharedViewModel(Application application) {
        super(application);
        links = new MutableLiveData<JSONObject>();
        teams = new MutableLiveData<ArrayList<Team>>();
        standingsEast = new MutableLiveData<ArrayList<Record>>();
        standingsWest = new MutableLiveData<ArrayList<Record>>();
        articles = new MutableLiveData<ArrayList<Article>>();
        games = new MutableLiveData<ArrayList<Game>>();
    }

    public MutableLiveData<JSONObject> getLinks() {
        if (links.getValue() == null)
            setLinks();
        return links;
    }

    public void setLinks() {
        endpoint = "today";
        new AsyncThread().execute("http://data.nba.net/prod/v1/today.json");
    }

    public MutableLiveData<ArrayList<Team>> getTeams() {
        if (teams.getValue() == null)
            setTeams();
        return teams;
    }

    public void setTeams() {
        endpoint = "teams";
        try {
            new AsyncThread().execute("http://data.nba.net" + getLinks().getValue().getString("teams"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashSet<String> getFavorites() {
        HashSet<String> set = new HashSet<String>();
        ArrayList<Team> listTeams = getTeams().getValue();
        for (int i = 0; i < listTeams.size(); i++) {
            Team team = listTeams.get(i);
            if (team.isFavorite())
                set.add(i + "");
        }
        Log.d("SHAH", set + "");
        Log.d("SHAH", teams.getValue() + "");
        return set;
    }

    public void setFavorites(Set<String> favorites) {
        ArrayList<Team> listTeams = getTeams().getValue();
        for (int i = 0; i < listTeams.size(); i++) {
            Team team = listTeams.get(i);
            if (favorites.contains(i))
                team.setFavorite(true);
            listTeams.set(i, team);
        }
        teams.setValue(listTeams);
    }

    public void setFavorite(int position, boolean favorite) {
        ArrayList<Team> list = teams.getValue();
        Team team = list.get(position);
        team.setFavorite(favorite);
        list.set(position, team);
        teams.setValue(list);
    }

    public MutableLiveData<ArrayList<Record>> getStandingsEast() {
        if (standingsEast.getValue() == null)
            setStandingsEast();
        return standingsEast;
    }

    public void setStandingsEast() {
        endpoint = "standingsEast";
        try {
            new AsyncThread().execute("http://data.nba.net" + getLinks().getValue().getString("leagueConfStandings"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MutableLiveData<ArrayList<Record>> getStandingsWest() {
        if (standingsWest.getValue() == null)
            setStandingsWest();
        return standingsWest;
    }

    public void setStandingsWest() {
        endpoint = "standingsWest";
        try {
            new AsyncThread().execute("http://data.nba.net" + getLinks().getValue().getString("leagueConfStandings"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getURL(int filter) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String url = "https://newsapi.org/v2/everything?apiKey=e65803cd6d86460496379f4cabcec8b2&language=en&pageSize=100&sortBy=relevancy&from=" + dateFormat.format(date) + "&q=";
        if (filter == 0)
            url += "nba";
        else if (filter == 1) {
            url += "nba%20and%20(";
            for (Team team : getTeams().getValue()) {
                if (team.isFavorite())
                    url += "(" + team.getFullName().toLowerCase().replace(" ", "%20") + ")%20or%20";
            }
            url = url.substring(0, url.length() - 8) + ")";
        }
        else {
            url += "nba%20and%20(" + getTeams().getValue().get(filter - 2).getFullName().toLowerCase().replace(" ", "%20") + ")";
        }
        return url;
    }

    public MutableLiveData<ArrayList<Article>> getArticles() {
        return articles;
    }

    public MutableLiveData<ArrayList<Article>> getArticles(int filter) {
        if (articles.getValue() == null)
            setArticles(filter);
        return articles;
    }

    public void setArticles(int filter) {
        endpoint = "newsapi";
        try {
            new AsyncThread().execute(getURL(filter));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MutableLiveData<ArrayList<Game>> getGames() {
        return games;
    }

    public MutableLiveData<ArrayList<Game>> getGames(String date) {
        if (games.getValue() == null)
            setGames(date);
        return games;
    }

    public void setGames(String date) {
        endpoint = "scoreboard";
        try {
            new AsyncThread().execute("http://data.nba.net" + getLinks().getValue().getString("scoreboard").replace("{{gameDate}}", date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class AsyncThread extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                String json = "";
                String line = null;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = bufferedReader.readLine()) != null)
                    json += line + "\n";
                bufferedReader.close();
                JSONObject jsonObject = new JSONObject(json);
                return jsonObject;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if (endpoint.equals("today"))
                    links.postValue(jsonObject.getJSONObject("links"));
                else if (endpoint.equals("teams")) {
                    SharedPreferences preferences = getApplication().getSharedPreferences("KEY_preferences", Context.MODE_PRIVATE);
                    HashSet<String> favorites = (HashSet<String>) preferences.getStringSet("KEY_favorites", null);
                    JSONArray jsonArray = jsonObject.getJSONObject("league").getJSONArray("standard");
                    ArrayList<Team> listTeams = new ArrayList<Team>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (jsonArray.getJSONObject(i).getBoolean("isNBAFranchise"))
                            listTeams.add(new Team(jsonArray.getJSONObject(i), false));
                    }
                    if (favorites != null && !favorites.isEmpty()) {
                        for (int i = 0; i < listTeams.size(); i++) {
                            Team team = listTeams.get(i);
                            team.setFavorite(favorites.contains(i + ""));
                            listTeams.set(i, team);
                        }
                    }
                    teams.postValue(listTeams);
                }
                else if (endpoint.equals("standingsEast")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("league").getJSONObject("standard").getJSONObject("conference").getJSONArray("east");
                    ArrayList<Record> listStandingsEast = new ArrayList<Record>();
                    for (int i = 0; i < jsonArray.length(); i++)
                        listStandingsEast.add(new Record(jsonArray.getJSONObject(i)));
                    standingsEast.postValue(listStandingsEast);
                }
                else if (endpoint.equals("standingsWest")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("league").getJSONObject("standard").getJSONObject("conference").getJSONArray("west");
                    ArrayList<Record> listStandingsWest = new ArrayList<Record>();
                    for (int i = 0; i < jsonArray.length(); i++)
                        listStandingsWest.add(new Record(jsonArray.getJSONObject(i)));
                    standingsWest.postValue(listStandingsWest);
                }
                else if (endpoint.equals("newsapi")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    ArrayList<Article> listArticles = new ArrayList<Article>();
                    for (int i = 0; i < jsonArray.length(); i++)
                        listArticles.add(new Article(jsonArray.getJSONObject(i)));
                    articles.postValue(listArticles);
                }
                else if (endpoint.equals("scoreboard")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("games");
                    ArrayList<Game> listGames = new ArrayList<Game>();
                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Log.d("TEST", jsonArray.getJSONObject(i).toString(2));
                        listGames.add(new Game(jsonArray.getJSONObject(i)));
                    }
                    games.postValue(listGames);
                    Log.d("SHAH", games.getValue() + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
