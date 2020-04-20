package com.example.fragmentdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TopFragment extends Fragment {

    Button buttonTop;
    TextView textTop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top, container, false);

        buttonTop = view.findViewById(R.id.id_buttonTop);
        textTop = view.findViewById(R.id.id_textTop);

        buttonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTop.setText("Clicked");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
