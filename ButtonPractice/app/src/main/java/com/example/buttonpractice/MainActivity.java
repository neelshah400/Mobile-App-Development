package com.example.buttonpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = (int)(Math.random() * 6) + 1;
                switch(random){
                    case 1:
                        ((Button)v).setTextColor(Color.RED);
                        break;
                    case 2:
                        ((Button)v).setTextColor(Color.YELLOW);
                        break;
                    case 3:
                        ((Button)v).setTextColor(Color.GREEN);
                        break;
                    case 4:
                        ((Button)v).setTextColor(Color.BLUE);
                        break;
                    case 5:
                        ((Button)v).setTextColor(Color.CYAN);
                        break;
                    case 6:
                        ((Button)v).setTextColor(Color.MAGENTA);
                        break;
                    default:
                        break;
                }
            }
        });

        //button1.setTooltipText("This button randomly sets its text color to 1 of 6 specified colors when clicked.");
        TooltipCompat.setTooltipText(button1, "This button randomly sets its text color to 1 of 6 specified colors when clicked.");

    }

    public void onClickButton2(View v) {
        String temp = (String)button1.getText();
        button1.setText(((Button)v).getText());
        ((Button)v).setText(temp);
    }

}
