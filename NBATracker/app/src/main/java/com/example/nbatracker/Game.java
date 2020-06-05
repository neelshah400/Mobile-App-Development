package com.example.nbatracker;

import android.util.Log;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Game {

    private String gameURL;
    private boolean activated;
    private String startTime;
    private String clock;
    private String hTricode;
    private String hWins;
    private String hLosses;
    private String hScore;
    private String vTricode;
    private String vWins;
    private String vLosses;
    private String vScore;

    public Game(JSONObject jsonObject) {
        try {
            gameURL = "https://www.nba.com/games/" + jsonObject.getString("gameUrlCode");
            activated = jsonObject.getBoolean("isGameActivated");

            startTime = jsonObject.getString("startTimeUTC");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = dateFormat.parse(startTime);
            dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            dateFormat.setTimeZone(TimeZone.getDefault());
            startTime = dateFormat.format(date);

            clock = jsonObject.getString("clock");
            hTricode = jsonObject.getJSONObject("hTeam").getString("triCode");
            hWins = jsonObject.getJSONObject("hTeam").getString("win");
            hLosses = jsonObject.getJSONObject("hTeam").getString("loss");
            hScore = jsonObject.getJSONObject("hTeam").getString("score");
            vTricode = jsonObject.getJSONObject("vTeam").getString("triCode");
            vWins = jsonObject.getJSONObject("vTeam").getString("win");
            vLosses = jsonObject.getJSONObject("vTeam").getString("loss");
            vScore = jsonObject.getJSONObject("vTeam").getString("score");
        } catch (Exception e) {
            Log.d("TEST", e.getMessage());
            e.printStackTrace();
        }
    }

    public String getGameURL() {
        return gameURL;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getHTricode() {
        return hTricode;
    }

    public void setHTricode(String hTricode) {
        this.hTricode = hTricode;
    }

    public String getHWins() {
        return hWins;
    }

    public void setHWins(String hWins) {
        this.hWins = hWins;
    }

    public String getHLosses() {
        return hLosses;
    }

    public void setHLosses(String hLosses) {
        this.hLosses = hLosses;
    }

    public String getHScore() {
        return hScore;
    }

    public void setHScore(String hScore) {
        this.hScore = hScore;
    }

    public String getVTricode() {
        return vTricode;
    }

    public void setVTricode(String vTricode) {
        this.vTricode = vTricode;
    }

    public String getVWins() {
        return vWins;
    }

    public void setVWins(String vWins) {
        this.vWins = vWins;
    }

    public String getVLosses() {
        return vLosses;
    }

    public void setVLosses(String vLosses) {
        this.vLosses = vLosses;
    }

    public String getVScore() {
        return vScore;
    }

    public void setVScore(String vScore) {
        this.vScore = vScore;
    }

    public String toString() {
        return hTricode + " vs. " + vTricode;
    }

}
