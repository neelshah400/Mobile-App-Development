package com.example.dallasmavericks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter<Player> {

    Context context;
    int resource;
    List<Player> objects;
    Player player;

    ImageView imagePlayer;
    TextView textName, textPosition, textAge, textHeight;
    ImageButton buttonToggle, buttonShare, buttonDelete;

    public PlayerAdapter(@NonNull Context context, int resource, @NonNull List<Player> objects) {

        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(resource, null);

        imagePlayer = adapterView.findViewById(R.id.id_imagePlayer);
        textName = adapterView.findViewById(R.id.id_textName);
        textPosition = adapterView.findViewById(R.id.id_textPosition);
        textAge = adapterView.findViewById(R.id.id_textAge);
        textHeight = adapterView.findViewById(R.id.id_textHeight);
        buttonToggle = adapterView.findViewById(R.id.id_buttonToggle);
        buttonShare = adapterView.findViewById(R.id.id_buttonShare);
        buttonDelete = adapterView.findViewById(R.id.id_buttonDelete);

        player = objects.get(position);

        imagePlayer.setImageResource(player.getImage());
        textName.setText(player.getName());
        textPosition.setText(player.getPosition());
        textAge.setText("" + player.getAge());
        textHeight.setText(player.getHeight());
        buttonToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).toggle();
            }
        });
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).share();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.remove(position);
                notifyDataSetChanged();
            }
        });

        if (((MainActivity) context).getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            buttonToggle.setVisibility(View.VISIBLE);
        else
            buttonToggle.setVisibility(View.GONE);

        return adapterView;

    }

}
