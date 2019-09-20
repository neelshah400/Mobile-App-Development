package com.example.buttonquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;
    int textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);
        button3 = findViewById(R.id.id_button3);

        textSize = 24;
        button3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

    }

    public void onClickButton1(View v) {
        button1.setTextColor(Color.RED);
    }

    public void onClickButton2(View v) {
        button2.setTextColor(Color.BLUE);
        button1.setText(button2.getText());
    }

    public void onClickButton3(View v) {
        textSize += 4;
        button3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

}
