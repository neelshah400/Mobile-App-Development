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
    TextView textTo, textTitle, textRate, textResult;
    Button buttonConvert;

    ArrayList<String> listUnits;
    ArrayList<Integer> listNumbers;
    ArrayAdapter<String> adapterUnits;
    String unitOrig, unitFinal;
    boolean selectOrig, selectFinal, selectNumber;
    double number, factor;
    int numOrig, numFinal;

    public static final String KEY_editNumber = "KEY_editNumber";
    public static final String KEY_spinnerOrig = "KEY_spinnerOrig";
    public static final String KEY_spinnerFinal = "KEY_spinnerFinal";

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
        textResult = findViewById(R.id.id_textResult);
        buttonConvert = findViewById(R.id.id_buttonConvert);;

        listUnits = new ArrayList<String>();
        listUnits.add("french fries");
        listUnits.add("mozarella sticks");
        listUnits.add("wraps");
        listUnits.add("burgers");

        listNumbers = new ArrayList<Integer>();
        listNumbers.add(1);
        listNumbers.add(4);
        listNumbers.add(16);
        listNumbers.add(32);

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
                if (s.toString().length() > 0) {
                    number = Double.parseDouble(s.toString());
                    selectNumber = true;
                }
                else
                    selectNumber = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectNumber) {
                    textResult.setText((number + " " + unitOrig + " = " + (number * factor) + " " + unitFinal).replace(".0 ", " "));
                }
            }
        });

        if (savedInstanceState != null) {
            number = savedInstanceState.getDouble(KEY_editNumber);
            editNumber.setText("" + number);
            unitOrig = savedInstanceState.getString(KEY_spinnerOrig);
            spinnerOrig.setSelection(listUnits.indexOf(unitOrig));
            unitFinal = savedInstanceState.getString(KEY_spinnerFinal);
            spinnerFinal.setSelection(listUnits.indexOf(unitFinal));
            displayRate();
            buttonConvert.performClick();
        }

    }

    public void displayRate() {
        numOrig = listNumbers.get(listUnits.indexOf(unitOrig));
        numFinal = listNumbers.get(listUnits.indexOf(unitFinal));
        factor = (double)numOrig / (double)numFinal;
        if (factor >= 1.0)
            textRate.setText("1 " + unitOrig + " = " + (int)factor + " " + unitFinal);
        else
            textRate.setText((int)(1.0 / factor) + " " + unitOrig + " = 1 " + unitFinal);
    }

    public int textToNum(String s) {

        if (s.equals("french fries"))
            return 1;
        else if (s.equals("french fries"))
            return 4;
        else if (s.equals("mozarella sticks"))
            return 16;
        else if (s.equals("burgers"))
            return 32;
        return 0;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putDouble(KEY_editNumber, number);
        outState.putString(KEY_spinnerOrig, unitOrig);
        outState.putString(KEY_spinnerFinal, unitFinal);

    }
}
