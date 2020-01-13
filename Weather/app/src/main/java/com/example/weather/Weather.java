package com.example.weather;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Weather {

    private Date date;
    private int temp;
    private int tempMin;
    private int tempMax;
    private String icon;
    private String description;

    public Weather(JSONObject json) {

        try {

            long unixTime = json.getLong("dt");
            date = new Date(unixTime * 1000);
            temp = (int)Math.round(json.getJSONObject("main").getDouble("temp"));
            tempMin = (int)Math.round(json.getJSONObject("main").getDouble("temp_min"));
            tempMax = (int)Math.round(json.getJSONObject("main").getDouble("temp_max"));
            icon = json.getJSONArray("weather").getJSONObject(0).getString("icon");

            description = json.getJSONArray("weather").getJSONObject(0).getString("description");
            String [] words = description.split(" ");
            description = "";
            for (String word : words) {
                word = word.substring(0, 1).toUpperCase() + word.substring(1);
                description += word + " ";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public int getTemp() {
        return temp;
    }

    public int getTempMin() {
        return tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public String getIcon() {
        return icon;
    }

    public int getImage() {

        int image = 0;
        switch(icon) {
            case "01d":
                image = R.drawable.sunny;
                break;
            case "01n":
                image = R.drawable.clear;
                break;
            case "02d":
                image = R.drawable.mostly_sunny;
                break;
            case "02n":
                image = R.drawable.mostly_clear;
                break;
            case "03d":
                image = R.drawable.partly_sunny;
                break;
            case "03n":
                image = R.drawable.partly_clear;
                break;
            case "04d":
                image = R.drawable.mostly_cloudy_day;
                break;
            case "04n":
                image = R.drawable.mostly_cloudy_night;
                break;
            case "09d":
                image = R.drawable.showers_day;
                break;
            case "09n":
                image = R.drawable.showers_night;
                break;
            case "10d":
                image = R.drawable.rain_day;
                break;
            case "10n":
                image = R.drawable.rain_night;
                break;
            case "11d":
                image = R.drawable.thunderstorm_day;
                break;
            case "11n":
                image = R.drawable.thunderstorm_night;
                break;
            case "13d":
                image = R.drawable.snow_day;
                break;
            case "13n":
                image = R.drawable.snow_night;
            case "50d":
                image = R.drawable.fog_day;
                break;
            case "50n":
                image = R.drawable.fog_night;
            default:
                break;
        }
        return image;

    }

    public String getDescription() {
        return description;
    }

    public String getQuotation() {

        String quotation = "";
        return quotation;

    }

}
