package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    TextView textZipCode, textCity, textDateTime, textTemperature, textDescription;
    EditText editZipCode;
    ImageButton buttonSearch;
    ImageView imageWeather;

    String zipCode;
    JSONObject jsonWeather, jsonForecast;
    Drawable drawable;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textZipCode = findViewById(R.id.id_textZipCode);
        textCity = findViewById(R.id.id_textCity);
        textDateTime = findViewById(R.id.id_textDateTime);
        textTemperature = findViewById(R.id.id_textTemperature);
        textDescription = findViewById(R.id.id_textDescription);
        editZipCode = findViewById(R.id.id_editZipCode);
        buttonSearch = findViewById(R.id.id_buttonSearch);
        imageWeather = findViewById(R.id.id_imageWeather);

        editZipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                zipCode = s + "";
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncThread asyncThread = new AsyncThread();
                asyncThread.execute(zipCode);
            }
        });

    }

    public class AsyncThread extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            String[] options = {"weather", "forecast"};

            try {

                for (String option : options) {
                    URL url = new URL("https://api.openweathermap.org/data/2.5/" + option + "?appid=a7922898315caae04278b4bc1f7760a9&units=imperial&zip=" + strings[0] + ",us");
                    URLConnection urlConnection = url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    String json = "";
                    String line = null;
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while ((line = bufferedReader.readLine()) != null)
                        json += line + "\n";
                    bufferedReader.close();
                    if (option.equals("weather"))
                        jsonWeather = new JSONObject(json);
                    else
                        jsonForecast = new JSONObject(json);
                }

                String icon = jsonWeather.getJSONArray("weather").getJSONObject(0).getString("icon");
                Log.d("SHAH", icon);
                Log.d("SHAH", "http://openweathermap.org/img/wn/" + icon + "@2x.png");

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
                        Log.d("SHAH", "error");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {

                textCity.setText(jsonWeather.getString("name"));
                textTemperature.setText(Math.round(jsonWeather.getJSONObject("main").getDouble("temp")) + "°");
                imageWeather.setImageResource(image);

                String description = jsonWeather.getJSONArray("weather").getJSONObject(0).getString("description");
                String [] words = description.split(" ");
                description = "";
                for (String word : words) {
                    word = word.substring(0, 1).toUpperCase() + word.substring(1);
                    description += word + " ";
                }
                textDescription.setText(description);

                long unixTime = jsonWeather.getLong("dt");
                Date date = new Date(unixTime * 1000);
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM DD, yyyy - HH:mm a");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
                String formattedDate = sdf.format(date);
                textDateTime.setText(formattedDate);
                Log.d("SHAH", formattedDate);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
