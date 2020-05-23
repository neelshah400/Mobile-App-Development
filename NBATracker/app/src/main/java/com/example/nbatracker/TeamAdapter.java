package com.example.nbatracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapter extends ArrayAdapter<Team> {

    Context context;
    int resource;
    List<Team> objects;
    Team team;

    ImageView imageTeam, imageStar;
    TextView textName;

    public TeamAdapter(@NonNull Context context, int resource, @NonNull List<Team> objects) {
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

        imageTeam = adapterView.findViewById(R.id.id_imageTeam);
        textName = adapterView.findViewById(R.id.id_textName);
        imageStar = adapterView.findViewById(R.id.id_imageStar);

        team = objects.get(position);
        Picasso.get().load("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/" + team.getTricode().toLowerCase() + ".png").into(imageTeam);
        textName.setText(team.getFullName());
        imageStar.setImageResource(team.isFavorite() ? R.drawable.ic_star : R.drawable.ic_star_border);

        return adapterView;

    }

}
