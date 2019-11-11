package com.example.widgetquiztwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioChoice;+
    Button buttonPlay;
    ImageView imageSelection;
    TextView textTotal, textResult;

    int playerChoice, cpuChoice, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioChoice = findViewById(R.id.id_radioChoice);
        buttonPlay = findViewById(R.id.id_buttonPlay);
        imageSelection = findViewById(R.id.id_imageSelection);
        textTotal = findViewById(R.id.id_textTotal);
        textResult = findViewById(R.id.id_textResult);

        radioChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                imageSelection.setImageResource(R.drawable.cpu_selection);
                textTotal.setText("Total");
                textResult.setText("Result");
                if (checkedId == R.id.id_button1)
                    playerChoice = 1;
                if (checkedId == R.id.id_button2)
                    playerChoice = 2;
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerChoice != 1 && playerChoice != 2) {
                    Toast error = Toast.makeText(MainActivity.this, "Please pick a number!", Toast.LENGTH_SHORT);
                    error.show();
                }
                else {
                    cpuChoice = (int) (Math.random() * 2) + 1;
                    if (cpuChoice == 1)
                        imageSelection.setImageResource(R.drawable.cpu_one);
                    if (cpuChoice == 2)
                        imageSelection.setImageResource(R.drawable.cpu_two);
                    total = playerChoice + cpuChoice;
                    if (total >= 1 && total <= 4)
                        textTotal.setText("Total is " + total);
                    if (total % 2 == 0)
                        textResult.setText("You Win");
                    else
                        textResult.setText("You Lose");
                }
            }
        });

    }

}
