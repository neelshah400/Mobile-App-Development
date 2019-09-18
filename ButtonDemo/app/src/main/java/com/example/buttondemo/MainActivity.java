package com.example.buttondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);
        button3 = findViewById(R.id.id_button3);

    }

    public void onClickButton1(View view) {
        int rand = (int)(Math.random() * 2) + 1;
        if(rand == 1)
            button1.setTextColor(Color.BLUE);
        else
            button1.setTextColor(Color.RED);
    }

    public void onClickButton2(View view) {
        button2.setTextColor(Color.GREEN);
    }

    public void onClickButton3(View view) {
        String temp = (String)button1.getText();
        button1.setText(button2.getText());
        button2.setText(temp);
    }

}
