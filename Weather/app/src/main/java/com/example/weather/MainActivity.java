package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    TextView textZipCode, textCity, textDateTime, textTemperature, textDescription;
    EditText editZipCode;
    ImageButton buttonSearch;
    ImageView imageWeather;

    String zipCode;
    JSONObject jsonWeather, jsonForecast;

    ListView listView;
    ArrayList<Weather> listWeather;

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

        listView = findViewById(R.id.id_listView);
        listWeather = new ArrayList<Weather>();

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

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {

                textCity.setText(jsonWeather.getString("name"));

                Weather weather = new Weather(jsonWeather);
                textDateTime.setText(weather.getFormattedDate("EEE, MMM DD, yyyy - h:mm a"));
                textDescription.setText(weather.getDescription());
                textTemperature.setText(weather.getTemp() + "°");
                imageWeather.setImageResource(weather.getImage());

                JSONArray arrayForecast = jsonForecast.getJSONArray("list");
                for (int i = 0; i < arrayForecast.length(); i++) {
                    JSONObject element = arrayForecast.getJSONObject(i);
                    listWeather.add(new Weather(element));
                }
                WeatherAdapter weatherAdapter = new WeatherAdapter(MainActivity.this, R.layout.adapter_weather, listWeather);
                listView.setAdapter(weatherAdapter);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
