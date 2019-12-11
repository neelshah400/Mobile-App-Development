package com.example.dallasmavericks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Player> listPlayers;
    TextView textValuePoints, textValueRebounds, textValueAssists, textValueSteals, textValueBlocks;
    WebView webView;

    Player player;
    int position;

    public static final String KEY_listPlayers = "KEY_listPlayers";
    public static final String KEY_position = "KEY_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_listView);
        textValuePoints = findViewById(R.id.id_textValuePoints);
        textValueRebounds = findViewById(R.id.id_textValueRebounds);
        textValueAssists = findViewById(R.id.id_textValueAssists);
        textValueSteals = findViewById(R.id.id_textValueSteals);
        textValueBlocks = findViewById(R.id.id_textValueBlocks);
        webView = findViewById(R.id.id_webView);

        listPlayers = new ArrayList<Player>();

        if (savedInstanceState != null) {
            listPlayers = (ArrayList<Player>) savedInstanceState.getSerializable(KEY_listPlayers);
            position = savedInstanceState.getInt(KEY_position);
            player = listPlayers.get(position);
            setFields(player);
        }
        else {
            listPlayers.add(new Player(R.drawable.luka_doncic, "Luka Doncic", "SF", 20, "6\' 7\"", 30.3, 10.1, 9.2, 1.4, 0.1, "UGj1Jzd9lWI"));
            listPlayers.add(new Player(R.drawable.kristaps_porzingis, "Kristaps Porzingis", "PF", 24, "7\' 3\"", 16.8, 8.9, 1.4, 0.6, 2.2, "rMrWNCfWnok"));
            listPlayers.add(new Player(R.drawable.tim_hardaway_jr, "Tim Hardaway Jr.", "SG", 27, "6\' 5\"", 12.4, 2.1, 1.7, 0.6, 0.1, "KisjrjZyLzY"));
            listPlayers.add(new Player(R.drawable.jj_barea, "J.J. Barea", "PG", 35, "5\' 10\"", 10.8, 1.5, 3.0, 0.5, 0.3, "g0dPIzYB1-E"));
            listPlayers.add(new Player(R.drawable.dwight_powell, "Dwight Powell", "PF", 28, "6\' 10\"", 8.5, 4.6, 1.3, 0.7, 0.4, "mReUkJ79YGo"));
            listPlayers.add(new Player(R.drawable.dorian_finney_smith, "Dorian Finney-Smith", "SF", 26, "6\' 7\"", 8.4, 5.0, 1.0, 0.6, 0.4, "dTg4agrxTH0"));
            listPlayers.add(new Player(R.drawable.seth_curry, "Seth Curry", "SG", 29, "6\' 2\"", 8.4, 1.8, 1.6, 0.6, 0.2, "b2ZpF5bpskk"));
            listPlayers.add(new Player(R.drawable.maxi_kleber, "Maxi Kleber", "PF", 27, "6\' 10\"", 8.4, 5.9, 1.1, 0.3, 0.7, "3CP5yl1-7mg"));
            listPlayers.add(new Player(R.drawable.delon_wright, "Delon Wright", "PG", 27, "6\' 5\"", 7.9, 3.6, 3.5, 1.2, 0.3, "vAI2d7sLEB8"));
            listPlayers.add(new Player(R.drawable.jalen_brunson, "Jalen Brunson", "PG", 23, "6\' 1\"", 7.5, 2.5, 2.7, 0.5, 0.0, "2WFhnKdUbZg"));
            listPlayers.add(new Player(R.drawable.boban_marjanovic, "Boban Marjanovic", "C", 31, "7\' 4\"", 6.7, 4.3, 0.6, 0.2, 0.1, "9raOLC_2ZOw"));
            listPlayers.add(new Player(R.drawable.justin_jackson, "Justin Jackson", "SF", 24, "6\' 7\"", 6.4, 2.6, 0.4, 0.2, 0.1, "2y7jIU_6Ir4"));
            listPlayers.add(new Player(R.drawable.courtney_lee, "Courtney Lee", "SG", 34, "6\' 5\"", 4.8, 0.4, 0.6, 0.0, 0.4, "8FbWaRLq8gg"));
            listPlayers.add(new Player(R.drawable.ryan_broekhoff, "Ryan Broekhoff", "SG", 29, "6\' 6\"", 3.0, 1.3, 0.8, 0.3, 0.0, "Itry98e53J0"));
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            webView.getSettings().setJavaScriptEnabled(true);

        final PlayerAdapter playerAdapter = new PlayerAdapter(this, R.layout.adapter_player, listPlayers);
        playerAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {

                super.onChanged();
                boolean exists = listPlayers.contains(player);
                if (!exists)
                    setFields();
                else {
                    Log.d("NEEL_SHAH", player.getName() + " " + exists);
                }

            }
        });
        listView.setAdapter(playerAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MainActivity.this.position = position;
                MainActivity.this.player = listPlayers.get(position);
                setFields(MainActivity.this.player);

            }
        });

    }

    public void setFields() {
        textValuePoints.setText("0.0");
        textValueRebounds.setText("0.0");
        textValueAssists.setText("0.0");
        textValueSteals.setText("0.0");
        textValueBlocks.setText("0.0");
    }

    public void setFields(Player player) {

        textValuePoints.setText("" + player.getPoints());
        textValueRebounds.setText("" + player.getRebounds());
        textValueAssists.setText("" + player.getAssists());
        textValueSteals.setText("" + player.getSteals());
        textValueBlocks.setText("" + player.getBlocks());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            webView.loadUrl("https://www.youtube.com/embed/" + player.getVideo());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_listPlayers, listPlayers);
        outState.putInt(KEY_position, position);

    }

}
