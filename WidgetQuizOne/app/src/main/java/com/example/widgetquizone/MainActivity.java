package com.example.widgetquizone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4;
    EditText editText1, editText2;
    Button button1;
    Switch switch1;

    String password1, password2;
    ArrayList<String> used;
    boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.id_textView1);
        textView2 = findViewById(R.id.id_textView2);
        textView3 = findViewById(R.id.id_textView3);
        textView4 = findViewById(R.id.id_textView4);
        editText1 = findViewById(R.id.id_editText1);
        editText2 = findViewById(R.id.id_editText2);
        button1 = findViewById(R.id.id_button1);
        switch1 = findViewById(R.id.id_switch1);

        switch1.setEnabled(false);

        used = new ArrayList<String>();
        used.add("test");
        used.add("123");
        used.add("password");
        used.add("abc");

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password1 = s.toString();
                valid = !used.contains(password1);
                if (valid)
                    textView3.setText("Password Not Used Previously");
                else
                    textView3.setText("Password Already Used");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid)
                    switch1.setChecked(password1.equals(password2));
                else
                    switch1.setChecked(false);
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    textView4.setText("Match");
                else
                    textView4.setText("Does Not Match");
            }
        });

    }

}
