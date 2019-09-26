package com.example.layoutpractice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3, button4, button5;
    TextView text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);
        button3 = findViewById(R.id.id_button3);
        button4 = findViewById(R.id.id_button4);
        button5 = findViewById(R.id.id_button5);

        text1 = findViewById(R.id.id_text1);
        text2 = findViewById(R.id.id_text2);
        text3 = findViewById(R.id.id_text3);
        text4 = findViewById(R.id.id_text4);
        text5 = findViewById(R.id.id_text5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.getText().equals("OFF"))
                    text1.setText("ON");
                else
                    text1.setText("OFF");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text2.getText().equals("OFF"))
                    text2.setText("ON");
                else
                    text2.setText("OFF");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text3.getText().equals("OFF"))
                    text3.setText("ON");
                else
                    text3.setText("OFF");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text4.getText().equals("OFF"))
                    text4.setText("ON");
                else
                    text4.setText("OFF");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text5.getText().equals("OFF"))
                    text5.setText("ON");
                else
                    text5.setText("OFF");
            }
        });

    }

}
