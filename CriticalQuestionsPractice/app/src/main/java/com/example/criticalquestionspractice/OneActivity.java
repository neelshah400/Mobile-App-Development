package com.example.criticalquestionspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OneActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        button = findViewById(R.id.id_button);
        textView = findViewById(R.id.id_textView);

        textView.setText(getIntent().getStringExtra(MainActivity.TEXT_CODE));

        Toast toast = Toast.makeText(this, "You have entered a new activity!", Toast.LENGTH_LONG);
        toast.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
