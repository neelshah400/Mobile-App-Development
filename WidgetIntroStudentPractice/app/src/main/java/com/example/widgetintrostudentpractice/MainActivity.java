package com.example.widgetintrostudentpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2;
    Switch aSwitch;
    EditText editText;
    Button button;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.id_textView1);
        textView2 = findViewById(R.id.id_textView2);
        aSwitch = findViewById(R.id.id_aSwitch);
        editText = findViewById(R.id.id_editText);
        button = findViewById(R.id.id_button);
        seekBar = findViewById(R.id.id_seekBar);

        seekBar.setEnabled(false);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                seekBar.setEnabled(isChecked);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String color = s.toString().toLowerCase();
                if (color.equals("red"))
                    textView2.setTextColor(Color.RED);
                else if (color.equals("blue"))
                    textView2.setTextColor(Color.BLUE);
                else if (color.equals("green"))
                    textView2.setTextColor(Color.GREEN);
                else
                    textView2.setTextColor(Color.BLACK);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int oldProgress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                button.setWidth(button.getWidth() + (progress - oldProgress) * 5);
                oldProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
