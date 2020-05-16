package com.example.nbatracker;

import org.json.JSONArray;
import org.json.JSONObject;

public class Team {

    private int id;
    private String abbreviation;
    private String city;
    private String conference;
    private String division;
    private String fullName;
    private String name;

    public Team(int id, String abbreviation, String city, String conference, String division, String fullName, String name) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.city = city;
        this.conference = conference;
        this.division = division;
        this.fullName = fullName;
        this.name = name;
    }

    public Team(JSONObject jsonObject) {
        try {
            id = jsonObject.getInt("id");
            abbreviation = jsonObject.getString("abbreviation");
            city = jsonObject.getString("city");
            conference = jsonObject.getString("conference");
            division = jsonObject.getString("division");
            fullName = jsonObject.getString("full_name");
            name = jsonObject.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return fullName;
    }

}