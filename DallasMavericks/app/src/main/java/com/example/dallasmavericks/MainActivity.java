package com.example.dallasmavericks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    TextView textValuePoints, textValueRebounds, textValueAssists, textValueSteals, textValueBlocks, textDescription;
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
        textDescription = findViewById(R.id.id_textDescription);
        webView = findViewById(R.id.id_webView);

        listPlayers = new ArrayList<Player>();

        if (savedInstanceState != null) {
            listPlayers = (ArrayList<Player>) savedInstanceState.getSerializable(KEY_listPlayers);
            position = savedInstanceState.getInt(KEY_position);
            if (listPlayers.size() > 0 && position >= 0) {
                player = listPlayers.get(position);
                setFields(player);
            }
        }
        else {
            listPlayers.add(new Player(R.drawable.luka_doncic, "Luka Doncic", "SF", 20, "6\' 7\"", 30.3, 10.1, 9.2, 1.4, 0.1, "At only 20 years old, Luka Doncic (the reigning Rookie of the Year) is nearly averaging a triple-double and is in contention for the MVP title. The sky is the limit for the Slovenian progidy.", "UGj1Jzd9lWI"));
            listPlayers.add(new Player(R.drawable.kristaps_porzingis, "Kristaps Porzingis", "PF", 24, "7\' 3\"", 16.8, 8.9, 1.4, 0.6, 2.2, "Recovering from an ACL injury that left him on the sidelines for 18 months, Kristaps Porzingis is already making a big impact, especially on the defensive end. If he can return to his prior form, the Mavericks could become unstoppable.", "rMrWNCfWnok"));
            listPlayers.add(new Player(R.drawable.tim_hardaway_jr, "Tim Hardaway Jr.", "SG", 27, "6\' 5\"", 12.4, 2.1, 1.7, 0.6, 0.1, "Inconsistent in the beginning of the season, many people were concerned with Tim Hardaway Jr.'s performance. Fortunately, he has made big improvements and earned his role in the starting lineup.", "KisjrjZyLzY"));
            listPlayers.add(new Player(R.drawable.jj_barea, "J.J. Barea", "PG", 35, "5\' 10\"", 10.8, 1.5, 3.0, 0.5, 0.3, "A former NBA champion, J.J. Barea is using his experience to help the younger players develop. He has been very effective in limited minutes and provides a great veteran presence for the team.", "g0dPIzYB1-E"));
            listPlayers.add(new Player(R.drawable.dwight_powell, "Dwight Powell", "PF", 28, "6\' 10\"", 8.5, 4.6, 1.3, 0.7, 0.4, "An elite finisher at the rim, Dwight Powell has been very effective on the court with the starting unit. He shoots very efficiently as well.", "mReUkJ79YGo"));
            listPlayers.add(new Player(R.drawable.dorian_finney_smith, "Dorian Finney-Smith", "SF", 26, "6\' 7\"", 8.4, 5.0, 1.0, 0.6, 0.4, "Dorian Finney-Smith is one of the Mavericks\' top defenders. He has been a valuable starter for the team.", "dTg4agrxTH0"));
            listPlayers.add(new Player(R.drawable.seth_curry, "Seth Curry", "SG", 29, "6\' 2\"", 8.4, 1.8, 1.6, 0.6, 0.2, "Seth Curry is an elite 3-point shooter with a lot of skill. Given his brother\'s talents, basketball clearly runs in the family.", "b2ZpF5bpskk"));
            listPlayers.add(new Player(R.drawable.maxi_kleber, "Maxi Kleber", "PF", 27, "6\' 10\"", 8.4, 5.9, 1.1, 0.3, 0.7, "Maxi Kleber is a valuable player with talents on both sides of the floor. As a big man, his ability to shoot and spread the floor makes Kleber dangerous for the opposing team.", "3CP5yl1-7mg"));
            listPlayers.add(new Player(R.drawable.delon_wright, "Delon Wright", "PG", 27, "6\' 5\"", 7.9, 3.6, 3.5, 1.2, 0.3, "Acquired this season by the Dallas Mavericks, Delon Wright has been an effective role player for the Mavericks. With his ability to guard multiple positions, Wright is a major defensive asset.", "vAI2d7sLEB8"));
            listPlayers.add(new Player(R.drawable.jalen_brunson, "Jalen Brunson", "PG", 23, "6\' 1\"", 7.5, 2.5, 2.7, 0.5, 0.0, "Jalen Brunson has been very efficient on the court despite limited minutes. The former National Player of the Year at Villanova University, Brunson is an effective leader in the second unit.", "2WFhnKdUbZg"));
            listPlayers.add(new Player(R.drawable.boban_marjanovic, "Boban Marjanovic", "C", 31, "7\' 4\"", 6.7, 4.3, 0.6, 0.2, 0.1, "Currently the tallest player in the NBA, Boban Marjanovic can dominate players inside the paint. While his playing time is limited due to fatigue, Marjanovic is typically unstoppable when he\'s on the floor.", "9raOLC_2ZOw"));
            listPlayers.add(new Player(R.drawable.justin_jackson, "Justin Jackson", "SF", 24, "6\' 7\"", 6.4, 2.6, 0.4, 0.2, 0.1, "With his shooting ability and offensive effort, Justin Jackson is proving to be a valuable player. His feisty style gives Jackson a lot of potential.", "2y7jIU_6Ir4"));
            listPlayers.add(new Player(R.drawable.courtney_lee, "Courtney Lee", "SG", 34, "6\' 5\"", 4.8, 0.4, 0.6, 0.0, 0.4, "Courtney Lee does not have a significant role on the team currently.", "8FbWaRLq8gg"));
            listPlayers.add(new Player(R.drawable.ryan_broekhoff, "Ryan Broekhoff", "SG", 29, "6\' 6\"", 3.0, 1.3, 0.8, 0.3, 0.0, "Ryan Broekhoff does not have a significant role on the team currently.", "Itry98e53J0"));
            setFields();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            webView.getSettings().setJavaScriptEnabled(true);

        PlayerAdapter playerAdapter = new PlayerAdapter(this, R.layout.adapter_player, listPlayers);
        playerAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {

                super.onChanged();
                if (!listPlayers.contains(player))
                    setFields();
                else {
                    position = listPlayers.indexOf(player);
                    player = listPlayers.get(position);
                    setFields(player);
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
        player = null;
        textValuePoints.setText("");
        textValueRebounds.setText("");
        textValueAssists.setText("");
        textValueSteals.setText("");
        textValueBlocks.setText("");
        position = -1;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            textDescription.setText("Select a player.");
            webView.setVisibility(View.GONE);
        }
    }

    public void setFields(Player player) {

        textValuePoints.setText("" + player.getPoints());
        textValueRebounds.setText("" + player.getRebounds());
        textValueAssists.setText("" + player.getAssists());
        textValueSteals.setText("" + player.getSteals());
        textValueBlocks.setText("" + player.getBlocks());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            textDescription.setText(player.getDescription());
            webView.setVisibility(View.GONE);
            webView.loadUrl("https://www.youtube.com/embed/" + player.getVideo());
        }

    }

    public void toggle() {

        if (player != null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (webView.getVisibility() == View.GONE)
                    webView.setVisibility(View.VISIBLE);
                else if (webView.getVisibility() == View.VISIBLE)
                    webView.setVisibility(View.GONE);
            }
        }

    }

    public void share() {

        if (player != null) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "" + player);
            startActivity(Intent.createChooser(intent, "Share via"));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_listPlayers, listPlayers);
        outState.putInt(KEY_position, position);

    }

}
