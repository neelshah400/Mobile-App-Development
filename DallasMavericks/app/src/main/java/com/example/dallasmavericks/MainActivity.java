package com.example.dallasmavericks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Player> listPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPlayers = new ArrayList<Player>();
        listPlayers.add(new Player(R.drawable.luka_doncic, "Luka Doncic", "SF", 20, "6'7", 0.0, 0.0, 0.0, 0.0, 0.0));

        PlayerAdapter playerAdapter = new PlayerAdapter(this, R.layout.adapter_player, listPlayers);

    }
}
