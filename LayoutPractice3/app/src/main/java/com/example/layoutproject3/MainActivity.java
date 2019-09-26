package com.example.layoutproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;
    boolean clicked1, clicked2, clicked3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);
        button3 = findViewById(R.id.id_button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked1 = true;
                if(clicked1 && clicked2 && clicked3) {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked2 = true;
                if(clicked1 && clicked2 && clicked3) {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked3 = true;
                if(clicked1 && clicked2 && clicked3) {
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                }
            }
        });

    }

}
