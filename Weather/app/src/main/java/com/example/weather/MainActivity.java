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
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    TextView textZipCode, textCity, textTemperature;
    EditText editZipCode;
    ImageButton buttonSearch;
    ImageView imageWeather;

    String zipCode;
    JSONObject jsonWeather, jsonForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textZipCode = findViewById(R.id.id_textZipCode);
        textCity = findViewById(R.id.id_textCity);
        textTemperature = findViewById(R.id.id_textTemperature);
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

            String [] options = {"weather", "forecast"};
            for (String option : options) {
                try {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {

                textCity.setText(jsonWeather.getString("name"));
                textTemperature.setText(Math.round(Double.parseDouble(jsonWeather.getJSONObject("main").getString("temp"))) + "°");

                String icon = jsonWeather.getJSONArray("weather").getJSONObject(0).getString("icon");
                Log.d("SHAH", icon);

                URL url = new URL("http://openweathermap.org/img/wn/" + icon + "@2x.png");
                InputStream inputStream = (InputStream) url.getContent();
                Drawable drawable = Drawable.createFromStream(inputStream, "src");
                imageWeather.setImageDrawable(drawable);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
