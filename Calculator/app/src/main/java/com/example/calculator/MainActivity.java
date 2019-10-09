package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textOutput, textLog;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonPlus, buttonMinus, buttonTimes, buttonDivide, buttonEquals, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textOutput = findViewById(R.id.id_textOutput);
        textLog = findViewById(R.id.id_textLog);

        button0 = findViewById(R.id.id_button0);
        button1 = findViewById(R.id.id_button1);
        button2 = findViewById(R.id.id_button2);
        button3 = findViewById(R.id.id_button3);
        button4 = findViewById(R.id.id_button4);
        button5 = findViewById(R.id.id_button5);
        button6 = findViewById(R.id.id_button6);
        button7 = findViewById(R.id.id_button7);
        button8 = findViewById(R.id.id_button8);
        button9 = findViewById(R.id.id_button9);
        buttonPlus = findViewById(R.id.id_buttonPlus);
        buttonMinus = findViewById(R.id.id_buttonMinus);
        buttonTimes = findViewById(R.id.id_buttonTimes);
        buttonDivide = findViewById(R.id.id_buttonDivide);
        buttonEquals = findViewById(R.id.id_buttonEquals);
        buttonClear = findViewById(R.id.id_buttonClear);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonTimes.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

    }

    public void onClick(View v) {
        Button btn = (Button) v;
        String str = (String) btn.getText();
        if ("0123456789".contains(str))
            textOutput.append(str);
        if ("+-*/".contains(str))
            textOutput.append(str);
        if ("=".contains(str)) {
            String exp = "" + textOutput.getText();
            StringTokenizer tkn = new StringTokenizer(exp, "+-*/", true);
            ArrayList<String> list = new ArrayList<String>();
            while (tkn.hasMoreTokens())
                list.add(tkn.nextToken());
            textLog.setText("" + list);
            try {
                while (list.size() > 1) {
                    int plus = list.indexOf("+");
                    int minus = list.indexOf("-");
                    int times = list.indexOf("*");
                    int divide = list.indexOf("/");
                    int index = -1;
                    if (times != -1 || divide != -1) {
                        index = times == -1 ? divide : times;
                        double first = Double.parseDouble(list.get(index - 1));
                        double second = Double.parseDouble(list.get(index + 1));
                        double value = 0.0;
                        if (list.get(index).equals("*"))
                            value = first * second;
                        else
                            value = first / second;
                        if ((double) ((int) value) == value)
                            list.set(index - 1, "" + (int) value);
                        else
                            list.set(index - 1, "" + value);
                        list.remove(index + 1);
                        list.remove(index);
                    } else if (plus != -1 || minus != -1) {
                        index = plus == -1 ? minus : plus;
                        double first = Double.parseDouble(list.get(index - 1));
                        double second = Double.parseDouble(list.get(index + 1));
                        double value = 0.0;
                        if (list.get(index).equals("+"))
                            value = first + second;
                        else
                            value = first - second;
                        if ((double) ((int) value) == value)
                            list.set(index - 1, "" + (int) value);
                        else
                            list.set(index - 1, "" + value);
                        list.remove(index + 1);
                        list.remove(index);
                    }
                }
            } catch (Exception e) {
                list = new ArrayList<String>();
                list.add("Error");
            }
            textOutput.setText(list.get(0));
        }
        if ("C".contains(str))
            textOutput.setText("");

    }

}
