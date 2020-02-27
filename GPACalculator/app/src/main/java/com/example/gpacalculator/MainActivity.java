package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    TextView textName, textGPA;
    Button buttonGrades;

    String name;
    double GPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.id_editName);
        textName = findViewById(R.id.id_textName);
        textGPA = findViewById(R.id.id_textGPA);
        buttonGrades = findViewById(R.id.id_buttonGrades);

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString();
                textName.setText("Name: " + name);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGrades = new Intent(MainActivity.this, GradesActivity.class);
            }
        });

    }

}
