package com.example.orientationpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView, alert;
    Spinner spinner;
    Button button;
    ArrayList<String> list;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.id_textView);
        alert = findViewById(R.id.id_alert);
        spinner = findViewById(R.id.id_spinner);
        button = findViewById(R.id.id_button);

        list = new ArrayList<String>();
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
            spinner.setAdapter(arrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    textView.setText(list.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    textView.setText("All Elements Removed");
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.clear();
                    arrayAdapter.notifyDataSetChanged();
                }
            });
        }
        else
            alert.setText("This app will only work in portrait mode. Please rotate your phone.");

    }

}
