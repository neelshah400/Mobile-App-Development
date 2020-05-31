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

public class RecordAdapter extends ArrayAdapter<Record> {

    Context context;
    int resource;
    List<Record> objects;
    Record record;

    TextView textRank, textTeam, textWins, textLosses, textGamesBehind, textL10;
    ImageView imageTeam;

    public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
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

        textRank = adapterView.findViewById(R.id.id_textRank);
        textTeam = adapterView.findViewById(R.id.id_textTeam);
        textWins = adapterView.findViewById(R.id.id_textWins);
        textLosses = adapterView.findViewById(R.id.id_textLosses);
        textGamesBehind = adapterView.findViewById(R.id.id_textGamesBehind);
        textL10 = adapterView.findViewById(R.id.id_textL10);
        imageTeam = adapterView.findViewById(R.id.id_imageTeam);

        record = objects.get(position);
        textRank.setText(record.getConfRank());
        textTeam.setText(record.getTeamTricode());
        textWins.setText(record.getWins());
        textLosses.setText(record.getLosses());
        textGamesBehind.setText(record.getGamesBehind());
        textL10.setText(record.getLast10_wins() + "-" + record.getLast10_losses());
        Picasso.get().load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/" + record.getTeamTricode().toLowerCase() + ".png").into(imageTeam);

        return adapterView;

    }

}
