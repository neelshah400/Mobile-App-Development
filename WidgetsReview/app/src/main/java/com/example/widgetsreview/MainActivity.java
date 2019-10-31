package com.example.widgetsreview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    RadioGroup radioVolume, radioRPS;
    ImageView imageCPU;
    Button buttonPlay;
    TextView textStatus, textScore;

    // 0 = Rock, 1 = Paper, 2 = Scissor
    int choicePlayer, choiceCPU, scorePlayer, scoreCPU;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        radioVolume = findViewById(R.id.id_radioVolume);
        radioRPS = findViewById(R.id.id_radioRPS);
        imageCPU = findViewById(R.id.id_imageCPU);
        buttonPlay = findViewById(R.id.id_buttonPlay);
        textStatus = findViewById(R.id.id_textStatus);
        textScore = findViewById(R.id.id_textScore);

        scorePlayer = 0;
        scoreCPU = 0;

        buttonPlay.setEnabled(false);
        
        radioVolume.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.id_radio100) {
                    Toast toastVolume = Toast.makeText(MainActivity.this, "High volume could damage your hearing!", Toast.LENGTH_SHORT);
                    toastVolume.show();
                }
            }
        });

        radioRPS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                buttonPlay.setEnabled(true);
                if (checkedId == R.id.id_radioRock)
                    choicePlayer = 0;
                if (checkedId == R.id.id_radioPaper)
                    choicePlayer = 1;
                if (checkedId == R.id.id_radioScissor)
                    choicePlayer = 2;
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceCPU = (int)(Math.random() * 3);
                if (choiceCPU == 0)
                    imageCPU.setImageResource(R.drawable.rock);
                if (choiceCPU == 1)
                    imageCPU.setImageResource(R.drawable.paper);
                if (choiceCPU == 2)
                    imageCPU.setImageResource(R.drawable.scissor);
                if (choicePlayer == 0) {
                    if (choiceCPU == 0)
                        textStatus.setText("You Tie!");
                    if (choiceCPU == 1)
                        textStatus.setText("You Lose!");
                    if (choiceCPU == 2)
                        textStatus.setText("You Win!");
                }
                if (choicePlayer == 1) {
                    if (choiceCPU == 0)
                        textStatus.setText("You Win!");
                    if (choiceCPU == 1)
                        textStatus.setText("You Tie!");
                    if (choiceCPU == 2)
                        textStatus.setText("You Lose!");
                }
                if (choicePlayer == 2) {
                    if (choiceCPU == 0)
                        textStatus.setText("You Lose!");
                    if (choiceCPU == 1)
                        textStatus.setText("You Win!");
                    if (choiceCPU == 2)
                        textStatus.setText("You Tie!");
                }
                if (textStatus.getText().equals("You Win!"))
                    scorePlayer++;
                if (textStatus.getText().equals("You Lose!"))
                    scoreCPU++;
                textScore.setText("Player: " + scorePlayer + " / CPU: " + scoreCPU);
            }
        });
        
    }
    
}
