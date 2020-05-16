package com.example.nbatracker;

import android.os.AsyncTask;

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
import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<JSONObject> links;
    private MutableLiveData<ArrayList<Team>> teams;
    private MutableLiveData<String> text;

    String endpoint;

    public SharedViewModel() {
        links = new MutableLiveData<JSONObject>();
        getLinks();
        teams = new MutableLiveData<ArrayList<Team>>();
        getTeams();
        text = new MutableLiveData<String>();
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

    public void setFavorite(int position, boolean favorite) {
        ArrayList<Team> list = teams.getValue();
        Team team = list.get(position);
        team.setFavorite(favorite);
        list.set(position, team);
        teams.setValue(list);
    }

    public void setText(String s) {
        text.setValue(s);
    }

    public LiveData<String> getText() {
        return text;
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
