package com.example.criticalquestionspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TwoActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    EditText editText;

    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        button = findViewById(R.id.id_button);
        textView = findViewById(R.id.id_textView);
        editText = findViewById(R.id.id_editText);

        textView.setText(getIntent().getStringExtra(MainActivity.TEXT_CODE));

        Toast toast = Toast.makeText(this, "You have entered a new activity!", Toast.LENGTH_LONG);
        toast.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent();
                intentBack.putExtra(MainActivity.INTENT_CODE, text);
                setResult(RESULT_OK, intentBack);
                finish();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text = s + "";
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
