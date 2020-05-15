package com.example.nbatracker.ui.standings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.nbatracker.R;

public class StandingsFragment extends Fragment {

    private StandingsViewModel standingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        standingsViewModel = ViewModelProviders.of(this).get(StandingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_standings, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        standingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

}
