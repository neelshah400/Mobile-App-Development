package com.example.nbatracker;

import org.json.JSONObject;

public class Record {

    private String teamId;
    private String wins;
    private String losses;
    private String gamesBehind;
    private String confRank;
    private String last10_wins;
    private String last10_losses;
    private String teamName;
    private String teamNickname;
    private String teamTricode;

    public Record(JSONObject jsonObject) {
        try {
            teamId = jsonObject.getString("teamId");
            wins = jsonObject.getString("win");
            losses = jsonObject.getString("loss");
            gamesBehind = jsonObject.getString("gamesBehind");
            confRank = jsonObject.getString("confRank");
            last10_wins = jsonObject.getString("lastTenWin");
            last10_losses = jsonObject.getString("lastTenLoss");
            teamName = jsonObject.getJSONObject("teamSitesOnly").getString("teamName");
            teamNickname = jsonObject.getJSONObject("teamSitesOnly").getString("teamNickname");
            teamTricode = jsonObject.getJSONObject("teamSitesOnly").getString("teamTricode");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getGamesBehind() {
        return gamesBehind;
    }

    public void setGamesBehind(String gamesBehind) {
        this.gamesBehind = gamesBehind;
    }

    public String getConfRank() {
        return confRank;
    }

    public void setConfRank(String confRank) {
        this.confRank = confRank;
    }

    public String getLast10_wins() {
        return last10_wins;
    }

    public void setLast10_wins(String last10_wins) {
        this.last10_wins = last10_wins;
    }

    public String getLast10_losses() {
        return last10_losses;
    }

    public void setLast10_losses(String last10_losses) {
        this.last10_losses = last10_losses;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamNickname() {
        return teamNickname;
    }

    public void setTeamNickname(String teamNickname) {
        this.teamNickname = teamNickname;
    }

    public String getTeamTricode() {
        return teamTricode;
    }

    public void setTeamTricode(String teamTricode) {
        this.teamTricode = teamTricode;
    }

    public String toString() {
        return teamName + " " + teamNickname;
    }

}
