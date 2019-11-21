package com.example.conversionproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editNumber;
    Spinner spinnerOrig, spinnerFinal;
    TextView textTo, textTitle, textRate;
    Button buttonConvert;

    ArrayList<String> listUnits;
    ArrayAdapter<String> adapterUnits;
    String unitOrig, unitFinal;
    boolean selectOrig, selectFinal, selectNumber;
    double number;
    int numOrig, numFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber = findViewById(R.id.id_editNumber);
        spinnerOrig = findViewById(R.id.id_spinnerOrig);
        spinnerFinal = findViewById(R.id.id_spinnerFinal);
        textTo = findViewById(R.id.id_textTo);
        textTitle = findViewById(R.id.id_textTitle);
        textRate = findViewById(R.id.id_textRate);
        buttonConvert = findViewById(R.id.id_buttonConvert);

        listUnits = new ArrayList<String>();
        listUnits.add("french fries"); // 1
        listUnits.add("mozarella sticks"); // 4
        listUnits.add("wraps"); // 16
        listUnits.add("burgers"); // 32

        adapterUnits = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listUnits);
        spinnerOrig.setAdapter(adapterUnits);
        spinnerFinal.setAdapter(adapterUnits);

        spinnerOrig.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitOrig = listUnits.get(position);
                selectOrig = true;
                if (selectOrig && selectFinal)
                    displayRate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectOrig = false;
            }
        });

        spinnerFinal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitFinal = listUnits.get(position);
                selectFinal = true;
                if (selectOrig && selectFinal)
                    displayRate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectFinal = false;
            }
        });

        editNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                number = Double.parseDouble(s.toString());
                selectNumber = true;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectOrig && selectFinal && selectNumber) {
                    numOrig = textToNum(unitOrig);
                }
            }
        });

    }

    public void displayRate() {

    }

    public int textToNum(String s) {
        return 0;
    }

}
