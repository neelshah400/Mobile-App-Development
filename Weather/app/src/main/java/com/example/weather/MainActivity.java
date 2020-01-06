package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    TextView textZipCode, textCity;
    EditText editZipCode;
    ImageButton buttonSearch;

    String zipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textZipCode = findViewById(R.id.id_textZipCode);
        textCity = findViewById(R.id.id_textCity);
        editZipCode = findViewById(R.id.id_editZipCode);
        buttonSearch = findViewById(R.id.id_buttonSearch);

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

    public class AsyncThread extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {

            try {
                URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?appid=a7922898315caae04278b4bc1f7760a9&units=imperial&zip=" + strings[0] + ",us");
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                String json = "";
                String line = null;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = bufferedReader.readLine()) != null)
                    json += line + "\n";
                bufferedReader.close();
                JSONObject jsonObject = new JSONObject(json);
                Log.d("SHAH", jsonObject.toString(2));
                return jsonObject;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {

            try {
                textCity.setText(jsonObject.getJSONObject("city").getString("name"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
