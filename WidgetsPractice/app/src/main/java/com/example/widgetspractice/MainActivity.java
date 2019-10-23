package com.example.widgetspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch switch1, switch2, switch3;
    TextView textColor, textVerify, textCheck, textSize;
    EditText editVerify, editCheck;
    Button buttonVerify, buttonCheck;
    SeekBar seekBar;

    boolean status1, status2, status3;
    String emailVerify, emailCheck;
    ArrayList<String> emails = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = findViewById(R.id.id_switch1);
        switch2 = findViewById(R.id.id_switch2);
        switch3 = findViewById(R.id.id_switch3);
        textColor = findViewById(R.id.id_textColor);
        textVerify = findViewById(R.id.id_textVerify);
        textCheck = findViewById(R.id.id_textCheck);
        textSize = findViewById(R.id.id_textSize);
        editVerify = findViewById(R.id.id_editVerify);
        editCheck = findViewById(R.id.id_editCheck);
        buttonVerify = findViewById(R.id.id_buttonVerify);
        buttonCheck = findViewById(R.id.id_buttonCheck);
        seekBar = findViewById(R.id.id_seekBar);

        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this);
        switch3.setOnCheckedChangeListener(this);

        editVerify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailVerify = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailVerify.contains("@") && emailVerify.contains(".com") && emailVerify.indexOf("@") < emailVerify.indexOf(".com"))
                    textVerify.setText("Verified");
                else
                    textVerify.setText("Not Verified");
            }
        });

        emails.add("person1@gmail.com");
        emails.add("person2@yahoo.com");
        emails.add("person3@outlook.com");

        editCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailCheck = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emails.contains(emailCheck))
                    textCheck.setText("In Database");
                else
                    textCheck.setText("Not In Database");
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSize.setTextSize(progress + 14);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.getText().toString().equals("1"))
            status1 = isChecked;
        else if (buttonView.getText().toString().equals("2"))
            status2 = isChecked;
        else if (buttonView.getText().toString().equals("3"))
            status3 = isChecked;

        if (status1 && status2 && status3)
            textColor.setTextColor(Color.BLUE);
        else if (status1 && status3 && !status2)
            textColor.setTextColor(Color.RED);
        else if (status3 && !status1 && !status2)
            textColor.setTextColor(Color.GREEN);
        else
            textColor.setTextColor(Color.GRAY);

    }

}
