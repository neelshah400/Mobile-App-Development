package com.example.nbatracker;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<JSONObject> links;
    private MutableLiveData<ArrayList<Team>> teams;
    private MutableLiveData<ArrayList<Article>> articles;

    String endpoint;

    public SharedViewModel() {
        links = new MutableLiveData<JSONObject>();
        getLinks();
        teams = new MutableLiveData<ArrayList<Team>>();
        getTeams();
        articles = new MutableLiveData<ArrayList<Article>>();
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
            for (Team team : teams.getValue()) {
                if (team.isFavorite())
                    url += "(" + team.getFullName().toLowerCase().replace(" ", "%20") + ")%20or%20";
            }
            url = url.substring(0, url.length() - 8) + ")";
        }
        else {
            url += "nba%20and%20(" + teams.getValue().get(filter - 2).getFullName().replace(" ", "%20") + ")";
        }
        return url;
    }

    public MutableLiveData<ArrayList<Article>> getArticles() {
        return articles;
    }

    public MutableLiveData<ArrayList<Article>> getArticles(int filter) {
        if (articles == null)
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

    public void setFavorite(int position, boolean favorite) {
        ArrayList<Team> list = teams.getValue();
        Team team = list.get(position);
        team.setFavorite(favorite);
        list.set(position, team);
        teams.setValue(list);
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
                    links.setValue(jsonObject.getJSONObject("links"));
                else if (endpoint.equals("teams")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("league").getJSONArray("standard");
                    ArrayList<Team> listTeams = new ArrayList<Team>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (jsonArray.getJSONObject(i).getBoolean("isNBAFranchise"))
                            listTeams.add(new Team(jsonArray.getJSONObject(i)));
                    }
                    teams.setValue(listTeams);
                }
                else if (endpoint.equals("newsapi")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    ArrayList<Article> listArticles = new ArrayList<Article>();
                    for (int i = 0; i < jsonArray.length(); i++)
                        listArticles.add(new Article(jsonArray.getJSONObject(i)));
                    articles.setValue(listArticles);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
