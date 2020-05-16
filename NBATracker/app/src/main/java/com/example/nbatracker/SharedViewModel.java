package com.example.nbatracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Team>> teams;
    private MutableLiveData<String> text;

    public SharedViewModel() {
        teams = new MutableLiveData<ArrayList<Team>>();
        text = new MutableLiveData<String>();
    }

    public MutableLiveData<ArrayList<Team>> getTeams() {
        return teams;
    }

    public void setTeams(JSONArray jsonArray) {
        try {
            ArrayList<Team> listTeams = new ArrayList<Team>();
            for (int i = 0; i < jsonArray.length(); i++)
                listTeams.add(new Team(jsonArray.getJSONObject(i)));
            teams.setValue(listTeams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setText(String s) {

        text.setValue(s);

    }

    public LiveData<String> getText() {

        return text;

    }

}
