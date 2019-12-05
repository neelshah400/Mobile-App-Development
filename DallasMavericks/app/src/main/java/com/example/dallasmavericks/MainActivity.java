package com.example.dallasmavericks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Player> listPlayers;
    TextView textValuePoints, textValueRebounds, textValueAssists, textValueSteals, textValueBlocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_listView);

        listPlayers = new ArrayList<Player>();
        listPlayers.add(new Player(R.drawable.luka_doncic, "Luka Doncic", "SF", 20, "6\' 7\"", 30.3, 10.1, 9.2, 1.4, 0.1));
        listPlayers.add(new Player(R.drawable.kristaps_porzingis, "Kristaps Porzingis", "PF", 24, "7\' 3\"", 16.8, 8.9, 1.4, 0.6, 2.2));
        listPlayers.add(new Player(R.drawable.tim_hardaway_jr, "Tim Hardaway Jr.", "SG", 27, "6\' 5\"", 12.4, 2.1, 1.7, 0.6, 0.1));
        listPlayers.add(new Player(R.drawable.jj_barea, "J.J. Barea", "PG", 35, "5\' 10\"", 10.8, 1.5, 3.0, 0.5, 0.3));
        listPlayers.add(new Player(R.drawable.dwight_powell, "Dwight Powell", "PF", 28, "6\' 10\"", 8.5, 4.6, 1.3, 0.7, 0.4));
        listPlayers.add(new Player(R.drawable.dorian_finney_smith, "Dorian Finney-Smith", "SF", 26, "6\' 7\"", 8.4, 5.0, 1.0, 0.6, 0.4));
        listPlayers.add(new Player(R.drawable.seth_curry, "Seth Curry", "SG", 29, "6\' 2\"", 8.4, 1.8, 1.6, 0.6, 0.2));
        listPlayers.add(new Player(R.drawable.maxi_kleber, "Maxi Kleber", "PF", 27, "6\' 10\"", 8.4, 5.9, 1.1, 0.3, 0.7));
        listPlayers.add(new Player(R.drawable.delon_wright, "Delon Wright", "PG", 27, "6\' 5\"", 7.9, 3.6, 3.5, 1.2, 0.3));
        listPlayers.add(new Player(R.drawable.jalen_brunson, "Jalen Brunson", "PG", 23, "6\' 1\"", 7.5, 2.5, 2.7, 0.5, 0.0));
        listPlayers.add(new Player(R.drawable.boban_marjanovic, "Boban Marjanovic", "C", 31, "7\' 4\"", 6.7, 4.3, 0.6, 0.2, 0.1));
        listPlayers.add(new Player(R.drawable.justin_jackson, "Justin Jackson", "SF", 24, "6\' 7\"", 6.4, 2.6, 0.4, 0.2, 0.1));
        listPlayers.add(new Player(R.drawable.courtney_lee, "Courtney Lee", "SG", 34, "6\' 5\"", 4.8, 0.4, 0.6, 0.0, 0.4));
        listPlayers.add(new Player(R.drawable.ryan_broekhoff, "Ryan Broekhoff", "SG", 29, "6\' 6\"", 3.0, 1.3, 0.8, 0.3, 0.0));

        PlayerAdapter playerAdapter = new PlayerAdapter(this, R.layout.adapter_player, listPlayers);
        listView.setAdapter(playerAdapter);

    }
}
