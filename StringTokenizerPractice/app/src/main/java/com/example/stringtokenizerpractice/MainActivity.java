package com.example.stringtokenizerpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    TextView text11, text12, text13, text21, text22, text23, text31, text32, text33, text41, text42, text43, text51, text52, text53;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text11 = findViewById(R.id.id_text11);
        text12 = findViewById(R.id.id_text12);
        text13 = findViewById(R.id.id_text13);
        text13.setText("" + count((String)text12.getText(), ".,?!-"));

        text21 = findViewById(R.id.id_text21);
        text22 = findViewById(R.id.id_text22);
        text23 = findViewById(R.id.id_text23);
        text23.setText("" + count((String)text22.getText(), ".?!"));

        text31 = findViewById(R.id.id_text31);
        text32 = findViewById(R.id.id_text32);
        text33 = findViewById(R.id.id_text33);
        text33.setText("" + (count((String)text32.getText(), " ") + 1));

        text41 = findViewById(R.id.id_text41);
        text42 = findViewById(R.id.id_text42);
        text43 = findViewById(R.id.id_text43);
        text43.setText("" + ((double)(count((String)text42.getText(), " ")+ 1) / (double)count((String)text42.getText(), ".?!")));

        text51 = findViewById(R.id.id_text51);
        text52 = findViewById(R.id.id_text52);
        text53 = findViewById(R.id.id_text53);
        ArrayList<String> words = new ArrayList<String>();
        StringTokenizer tkn = new StringTokenizer((String)text52.getText(), " ", false);
        while(tkn.hasMoreTokens())
            words.add(tkn.nextToken());
        int cnt = 0;
        for(String str : words) {
            if(count(str, "aeiouAEIOU") > 1)
                cnt++;
        }
        text53.setText("" + cnt);

    }

    public int count(String str, String delim) {
        ArrayList<String> list = new ArrayList<String>();
        StringTokenizer tkn = new StringTokenizer(str, delim, true);
        while(tkn.hasMoreTokens())
            list.add(tkn.nextToken());
        for(int i = list.size() - 1; i >= 0; i--) {
            if(!delim.contains(list.get(i)))
                list.remove(i);
        }
        return list.size();
    }

}

