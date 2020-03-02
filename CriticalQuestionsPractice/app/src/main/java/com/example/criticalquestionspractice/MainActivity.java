package com.example.criticalquestionspractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    EditText editText;
    TextView textTyped, textResult;
    ImageView imageView;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    Button button1, button2;

    ArrayList<String> list;
    ArrayAdapter adapter;

    static final String TEXT_CODE = "TEXT";
    static final int NUMBER_CODE = 1234;
    static final String INTENT_CODE = "INTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.id_constraintLayout);
        editText = findViewById(R.id.id_editText);
        textTyped = findViewById(R.id.id_textTyped);
        textResult = findViewById(R.id.id_textResult);
        imageView = findViewById(R.id.id_imageView);
        spinner = findViewById(R.id.id_spinner);
        radioGroup = findViewById(R.id.id_radioGroup);
        radioButton1 = findViewById(R.id.id_radioButton1);
        radioButton2 = findViewById(R.id.id_radioButton2);
        radioButton3 = findViewById(R.id.id_radioButton3);
        radioButton4 = findViewById(R.id.id_radioButton4);
        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textTyped.setText(s + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        list = new ArrayList<String>();
        list.add("White");
        list.add("Red");
        list.add("Blue");
        list.add("Green");
        list.add("Yellow");
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = list.get(position);
                if (selection.equals("White"))
                    constraintLayout.setBackgroundColor(Color.WHITE);
                else if (selection.equals("Red"))
                    constraintLayout.setBackgroundColor(Color.RED);
                else if (selection.equals("Blue"))
                    constraintLayout.setBackgroundColor(Color.BLUE);
                else if (selection.equals("Green"))
                    constraintLayout.setBackgroundColor(Color.GREEN);
                else if (selection.equals("Yellow"))
                    constraintLayout.setBackgroundColor(Color.YELLOW);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                constraintLayout.setBackgroundColor(Color.WHITE);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButton1.getId())
                    imageView.setImageResource(R.drawable.sourpatch);
                else if (checkedId == radioButton2.getId())
                    imageView.setImageResource(R.drawable.skittles);
                else if (checkedId == radioButton3.getId())
                    imageView.setImageResource(R.drawable.twizzlers);
                else if (checkedId == radioButton4.getId())
                    imageView.setImageResource(R.drawable.airheads);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, OneActivity.class);
                intent1.putExtra(TEXT_CODE, "This is some text.");
                startActivity(intent1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, TwoActivity.class);
                intent2.putExtra(TEXT_CODE, "This is some text.");
                startActivityForResult(intent2, NUMBER_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NUMBER_CODE && resultCode == RESULT_OK)
            textResult.setText(data.getStringExtra(INTENT_CODE));

    }
}
