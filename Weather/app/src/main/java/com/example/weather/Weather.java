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
        if (icon.equals("01d") || icon.equals("01n"))
            quotation = "I solemnly swear that there isn't a cloud in the sky. - Harry Potter";
        else if (icon.equals("02d") || icon.equals("02n"))
            quotation = "It does not do to dwell on dreams and forget to live. - Albus Dumbledore";
        else if (icon.equals("03d") || icon.equals("03n"))
            quotation = "No good sittin’ worryin’ abou’ the clouds. What’s comin’ will come, an’ we’ll meet it when it does. - Rubeus Hagrid";
        else if (icon.equals("04d"))
            quotation = "Sunshine can be found even in the cloudiest of times, if one only remembers to turn on the light. - Albus Dumbledore";
        else if (icon.equals("04n"))
            quotation = "It is the unknown we fear when we look upon clouds and darkness, nothing more. - Albus Dumbledore";
        else if (icon.equals("09d") || icon.equals("09n"))
            quotation = "Fear of a rain shower only increases fear of the thing itself. - Albus Dumbledore";
        else if (icon.equals("10d"))
            quotation = "Water is, in my not so humble opinion, our most inexhaustible source of magic, capable of both inflicting injury and remedying it. - Albus Dumbledore";
        else if (icon.equals("10n"))
            quotation = "Dark and rainy times lie ahead. Soon we must all face the choice between what is right and what is easy. - Albus Dumbledore";
        else if (icon.equals("11d") || icon.equals("11n"))
            quotation = "Thunderstorms could leave deeper scars than almost anything else. - Madam Poppy Pomfrey";
        else if (icon.equals("13d") || icon.equals("13n"))
            quotation = "To the well-organized mind, snow is but the next great adventure. - Albus Dumbledore";
        else if (icon.equals("50d") || icon.equals("50n"))
            quotation = "Time will not slow down when mist lies ahead. - Harry Potter";
        return quotation;

    }

}
