package com.example.dallasmavericks;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter<Player> {

    Context context;
    int resource;
    List<Player> objects;

    public PlayerAdapter(@NonNull Context context, int resource, @NonNull List<Player> objects) {

        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(resource, null);

        ImageView imagePlayer = adapterView.findViewById(R.id.id_imagePlayer);
        TextView textName = adapterView.findViewById(R.id.id_textName);
        TextView textPosition = adapterView.findViewById(R.id.id_textPosition);
        TextView textAge = adapterView.findViewById(R.id.id_textAge);
        TextView textHeight = adapterView.findViewById(R.id.id_textHeight);
        Button buttonDelete = adapterView.findViewById(R.id.id_buttonDelete);

        Player player = objects.get(position);

        imagePlayer.setImageResource(player.getImage());
        textName.setText(player.getName());
        textPosition.setText(player.getPosition());
        textAge.setText("" + player.getAge());
        textHeight.setText(player.getHeight());

        return adapterView;

    }
}
