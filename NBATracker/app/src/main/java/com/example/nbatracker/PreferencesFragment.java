package com.example.nbatracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PreferencesFragment extends Fragment {

    private SharedViewModel model;

    ListView listView;
    TeamAdapter teamAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_preferences, container, false);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        listView = root.findViewById(R.id.id_listView);
        teamAdapter = new TeamAdapter(getContext(), R.layout.adapter_team, model.getTeams().getValue());
        listView.setAdapter(teamAdapter);

        return root;

    }

}
