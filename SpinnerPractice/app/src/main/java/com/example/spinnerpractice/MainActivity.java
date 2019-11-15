package com.example.spinnerpractice;

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

    TextView textPrefix, textName, textNames;
    Spinner spinnerPrefix, spinnerName;
    EditText editName;
    Button buttonAdd;

    ArrayList<String> listPrefixes, listNames;
    ArrayAdapter adapterPrefixes, adapterNames;
    String prefix, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textPrefix = findViewById(R.id.id_textPrefix);
        textName = findViewById(R.id.id_textName);
        textNames = findViewById(R.id.id_textNames);
        spinnerPrefix = findViewById(R.id.id_spinnerPrefix);
        spinnerName = findViewById(R.id.id_spinnerName);
        editName = findViewById(R.id.id_editName);
        buttonAdd = findViewById(R.id.id_buttonAdd);

        listPrefixes = new ArrayList<String>();
        listPrefixes.add("Mr.");
        listPrefixes.add("Ms.");
        listPrefixes.add("Mrs.");
        listPrefixes.add("Dr.");
        listPrefixes.add("Sir");

        prefix = "";
        name = "";

        adapterPrefixes = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listPrefixes);
        spinnerPrefix.setAdapter(adapterPrefixes);

        spinnerPrefix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prefix = listPrefixes.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                prefix = "";
            }
        });

        listNames = new ArrayList<String>();
        adapterNames = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listNames);
        spinnerName.setAdapter(adapterNames);

        spinnerName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!prefix.equals("") && !name.equals("")) {
                    textName.setText(prefix + " " + name);
                    listNames.add(textName.getText().toString());
                    adapterNames.notifyDataSetChanged();
                    spinnerName.setAdapter(adapterNames);
                }
                else
                    textName.setText("");
            }
        });

    }

}
