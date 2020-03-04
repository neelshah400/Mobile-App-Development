package com.example.criticalquestions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        textView = findViewById(R.id.id_textView);
        String selection = getIntent().getStringExtra("SELECTION");
        textView.setText("The " + selection + " Radio Button was selected");

    }

}
