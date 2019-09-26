package com.example.layoutpractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout1;
    Button button1, button2, button3, button4, button5, button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.id_layout1);

        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);
        button3 = findViewById(R.id.id_button3);
        button4 = findViewById(R.id.id_button4);
        button5 = findViewById(R.id.id_button5);
        button6 = findViewById(R.id.id_button6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4.setTextColor(Color.RED);
                button5.setTextColor(Color.RED);
                button6.setTextColor(Color.RED);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4.setTextColor(Color.BLUE);
                button5.setTextColor(Color.BLUE);
                button6.setTextColor(Color.BLUE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button4.setTextColor(Color.GREEN);
                button5.setTextColor(Color.GREEN);
                button6.setTextColor(Color.GREEN);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setBackgroundColor(Color.CYAN);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setBackgroundColor(Color.GRAY);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setBackgroundColor(Color.MAGENTA);
            }
        });


    }

}
