package com.example.criticalquestions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;
    TextView textName, textBlock;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3;
    Button buttonRun, buttonLaunch;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.id_layout);
        textName = findViewById(R.id.id_textName);
        textBlock = findViewById(R.id.id_textBlock);
        radioGroup = findViewById(R.id.id_radioGroup);
        rb1 = findViewById(R.id.id_rb1);
        rb2 = findViewById(R.id.id_rb2);
        rb3 = findViewById(R.id.id_rb3);
        buttonRun = findViewById(R.id.id_buttonRun);
        buttonLaunch = findViewById(R.id.id_buttonLaunch);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                id = checkedId;
            }
        });

        buttonRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == rb1.getId()) {
                    Toast toast = Toast.makeText(MainActivity.this, "Toast Selected", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if (id == rb2.getId()) {
                    int [] color = {Color.RED, Color.GREEN, Color.BLUE};
                    int rand = (int)(Math.random() * 3);
                    layout.setBackgroundColor(color[rand]);
                }
                else if (id == rb3.getId()) {
                    String name = textName.getText() + "";
                    textName.setText(name.toUpperCase());
                }
            }
        });

        buttonLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                RadioButton rb = findViewById(id);
                intent.putExtra("SELECTION", rb.getText() + "");
                startActivity(intent);
            }
        });

    }

}
