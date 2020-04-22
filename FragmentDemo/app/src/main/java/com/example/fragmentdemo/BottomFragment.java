package com.example.fragmentdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BottomFragment extends Fragment {

    Button buttonChange;
    SendInfo sendInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        buttonChange = view.findViewById(R.id.id_buttonChange);

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInfo.update("UPDATE");
            }
        });

        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendInfo = (SendInfo) context;
    }

    public interface SendInfo {
        public void update(String str);
    }

}
