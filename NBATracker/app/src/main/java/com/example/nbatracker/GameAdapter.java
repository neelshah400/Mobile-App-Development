package com.example.nbatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GameAdapter extends ArrayAdapter<Game> {

    Context context;
    int resource;
    List<Game> objects;
    Game game;

    ImageView imageH, imageV;
    TextView textTeamH, textTeamV, textRecordH, textRecordV, textScoreH, textScoreV, textStatus;

    public GameAdapter(@NonNull Context context, int resource, @NonNull List<Game> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(resource, null);

        imageH = adapterView.findViewById(R.id.id_imageH);
        imageV = adapterView.findViewById(R.id.id_imageV);
        textTeamH = adapterView.findViewById(R.id.id_textTeamH);
        textTeamV = adapterView.findViewById(R.id.id_textTeamV);
        textRecordH = adapterView.findViewById(R.id.id_textRecordH);
        textRecordV = adapterView.findViewById(R.id.id_textRecordV);
        textScoreH = adapterView.findViewById(R.id.id_textScoreH);
        textScoreV = adapterView.findViewById(R.id.id_textScoreV);
        textStatus = adapterView.findViewById(R.id.id_textStatus);

        game = objects.get(position);
        Picasso.get().load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/" + game.getHTricode().toLowerCase() + ".png").into(imageH);
        Picasso.get().load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/" + game.getVTricode().toLowerCase() + ".png").into(imageV);
        textTeamH.setText(game.getHTricode());
        textTeamV.setText(game.getVTricode());
        textRecordH.setText(game.getHWins() + "-" + game.getHLosses());
        textRecordV.setText(game.getVWins() + "-" + game.getVLosses());
        textScoreH.setText(game.getHScore());
        textScoreV.setText(game.getVScore());
        if (game.isActivated())
            textStatus.setText(game.getClock());
        else
            textStatus.setText("Final");

        int h = Integer.parseInt(game.getHScore());
        int v = Integer.parseInt(game.getVScore());
        if (h > v)
            textScoreH.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
        else if (v > h)
            textScoreV.setTextColor(getContext().getResources().getColor(R.color.colorAccent));

        return adapterView;

    }

}
