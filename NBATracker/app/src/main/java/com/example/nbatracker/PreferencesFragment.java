package com.example.nbatracker;

import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class PreferencesFragment extends Fragment {

    private SharedViewModel model;

    ListView listView;
    TeamAdapter teamAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_preferences, container, false);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        listView = root.findViewById(R.id.id_listView);

        model.getTeams().observe(getViewLifecycleOwner(), new Observer<ArrayList<Team>>() {
            @Override
            public void onChanged(ArrayList<Team> teams) {
                int pos = listView.getFirstVisiblePosition();
                View v = listView.getChildAt(0);
                int offset = (v == null) ? 0 : v.getTop();
                teamAdapter = new TeamAdapter(getContext(), R.layout.adapter_team, model.getTeams().getValue());
                listView.setAdapter(teamAdapter);
                listView.setSelectionFromTop(pos, offset);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model.setFavorite(position, !model.getTeams().getValue().get(position).isFavorite());
            }
        });

        return root;

    }

}
