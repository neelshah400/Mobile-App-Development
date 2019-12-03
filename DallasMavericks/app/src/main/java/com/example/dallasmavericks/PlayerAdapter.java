package com.example.dallasmavericks;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

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

}
