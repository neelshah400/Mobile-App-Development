package com.example.datademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    String file;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.id_textView);

        file = "data.txt";
        message = "This is a test";

        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(file, Context.MODE_PRIVATE));
            writer.write(message);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String lines = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(file)));
            String line = reader.readLine();
            while (line != null) {
                lines += line;
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        textView.setText(lines);

    }

}
