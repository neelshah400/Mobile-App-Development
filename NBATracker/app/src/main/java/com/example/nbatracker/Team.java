package com.example.nbatracker;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Team implements Serializable {

    private boolean isNBAFranchise;
    private boolean isAllStar;
    private String city;
    private String altCityName;
    private String fullName;
    private String tricode;
    private int teamId;
    private String nickname;
    private String urlName;
    private String teamShortName;
    private String confName;
    private String divName;

    private boolean favorite;

    private int wins;
    private int losses;
    private int gamesBehind;
    private int confRank;
    private int last10_wins;
    private int last10_losses;

    public Team(JSONObject jsonObject) {
        try {
            isNBAFranchise = jsonObject.getBoolean("isNBAFranchise");
            isAllStar = jsonObject.getBoolean("isAllStar");
            city = jsonObject.getString("city");
            altCityName = jsonObject.getString("altCityName");
            fullName = jsonObject.getString("fullName");
            tricode = jsonObject.getString("tricode");
            teamId = Integer.parseInt(jsonObject.getString("teamId"));
            nickname = jsonObject.getString("nickname");
            urlName = jsonObject.getString("urlName");
            teamShortName = jsonObject.getString("teamShortName");
            confName = jsonObject.getString("confName");
            divName = jsonObject.getString("divName");
            favorite = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addHistory(JSONObject jsonObject) {
        try {
            wins = Integer.parseInt(jsonObject.getString("win"));
            losses = Integer.parseInt(jsonObject.getString("loss"));
            gamesBehind = Integer.parseInt(jsonObject.getString("gamesBehind"));
            confRank = Integer.parseInt(jsonObject.getString("confRank"));
            last10_wins = Integer.parseInt(jsonObject.getString("lastTenWin"));
            last10_losses = Integer.parseInt(jsonObject.getString("lastTenLoss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNBAFranchise() {
        return isNBAFranchise;
    }

    public void setNBAFranchise(boolean NBAFranchise) {
        isNBAFranchise = NBAFranchise;
    }

    public boolean isAllStar() {
        return isAllStar;
    }

    public void setAllStar(boolean allStar) {
        isAllStar = allStar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAltCityName() {
        return altCityName;
    }

    public void setAltCityName(String altCityName) {
        this.altCityName = altCityName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTricode() {
        return tricode;
    }

    public void setTricode(String tricode) {
        this.tricode = tricode;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getTeamShortName() {
        return teamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        this.teamShortName = teamShortName;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String toString() {
        return fullName + (favorite ? "*" : "");
    }

}