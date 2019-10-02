package com.example.layoutquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);
        text1 = findViewById(R.id.id_text1);
        text2 = findViewById(R.id.id_text2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.getText().equals("Not Clicked"))
                    text1.setText("Clicked");
                else
                    text1.setText("Not Clicked");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text2.getText().equals("Not Clicked"))
                    text2.setText("Clicked");
                else
                    text2.setText("Not Clicked");
            }
        });

    }

}
