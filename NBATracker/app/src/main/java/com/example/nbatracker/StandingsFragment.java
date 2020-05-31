package com.example.nbatracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class StandingsFragment extends Fragment {

    private SharedViewModel model;

    TabLayout tabLayout;
    ListView listView;
    RecordAdapter recordAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_standings, container, false);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        tabLayout = root.findViewById(R.id.id_tabLayout);
        listView = root.findViewById(R.id.id_listView);

        model.getStandingsEast().observe(getViewLifecycleOwner(), new Observer<ArrayList<Record>>() {
            @Override
            public void onChanged(ArrayList<Record> records) {
                recordAdapter = new RecordAdapter(getContext(), R.layout.adapter_record, records);
                listView.setAdapter(recordAdapter);
            }
        });

        model.getStandingsWest().observe(getViewLifecycleOwner(), new Observer<ArrayList<Record>>() {
            @Override
            public void onChanged(ArrayList<Record> records) {
                recordAdapter = new RecordAdapter(getContext(), R.layout.adapter_record, records);
                listView.setAdapter(recordAdapter);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0)
                    model.setStandingsEast();
                else
                    model.setStandingsWest();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        model.setStandingsEast();

        return root;

    }

}
