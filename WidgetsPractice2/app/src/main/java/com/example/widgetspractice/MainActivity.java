package com.example.widgetspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.id_radioGroup);
        imageView = findViewById(R.id.id_imageView);

        imageView.setImageResource(R.drawable.harry_potter_logo);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.id_radio1:
                        imageView.setImageResource(R.drawable.harry);
                        Toast toast1 = Toast.makeText(MainActivity.this, "You have selected Harry!", Toast.LENGTH_SHORT);
                        toast1.show();
                        break;
                    case R.id.id_radio2:
                        imageView.setImageResource(R.drawable.hermione);
                        Toast toast2 = Toast.makeText(MainActivity.this, "You have selected Hermione!", Toast.LENGTH_SHORT);
                        toast2.show();
                        break;
                    case R.id.id_radio3:
                        imageView.setImageResource(R.drawable.ron);
                        Toast toast3 = Toast.makeText(MainActivity.this, "You have selected Ron!", Toast.LENGTH_SHORT);
                        toast3.show();
                        break;
                    default:
                        break;
                }
            }
        });

    }

}
