package com.example.exceptionspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    TextView text11, text12, text13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text11 = findViewById(R.id.id_text11);
        text12 = findViewById(R.id.id_text12);
        text13 = findViewById(R.id.id_text13);
        ArrayList<String> a1 = new ArrayList<String>();
        StringTokenizer t1 = new StringTokenizer((String)text12.getText(), ".,?!-", true);
        while(t1.hasMoreTokens())
            a1.add(t1.nextToken());
        for(int i = a1.size() - 1; i >= 0; i--) {
            if(!".,?!-".contains(a1.get(i)))
                a1.remove(i);
        }
        text13.setText("" + a1.size());

    }

}
