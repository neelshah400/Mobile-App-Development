package com.example.listviewpractice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Pokemon> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_listView);

        arrayList = new ArrayList<Pokemon>();
        arrayList.add(new Pokemon(R.drawable.bulbasaur, "Bulbasaur", 1, "strength", "weakness"));
        arrayList.add(new Pokemon(R.drawable.ivysaur, "Ivysaur", 1, "strength", "weakness"));
        arrayList.add(new Pokemon(R.drawable.venusaur, "Venusaur", 1, "strength", "weakness"));
        arrayList.add(new Pokemon(R.drawable.charmander, "Charmander", 1, "strength", "weakness"));
        arrayList.add(new Pokemon(R.drawable.charmeleon, "Charmeleon", 1, "strength", "weakness"));

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, arrayList);
        listView.setAdapter(customAdapter);

    }

    public class Pokemon {

        private int image;
        private String name;
        private int level;
        private String strength;
        private String weakness;

        public Pokemon(int image, String name, int level, String strength, String weakness) {
            this.image = image;
            this.name = name;
            this.level = level;
            this.strength = strength;
            this.weakness = weakness;
        }

        public int getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public String getStrength() {
            return strength;
        }

        public String getWeakness() {
            return weakness;
        }

        public void setLevel(int level) {
            this.level = level;
        }

    }

    public class CustomAdapter extends ArrayAdapter<Pokemon> {

        Context context;
        int resource;
        List<Pokemon> objects;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Pokemon> objects) {

            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.objects = objects;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = layoutInflater.inflate(resource, null);

            ImageView imageView = adapterView.findViewById(R.id.id_imageView);
            TextView textName = adapterView.findViewById(R.id.id_textName);
            TextView textStrength = adapterView.findViewById(R.id.id_textStrength);
            TextView textWeakness = adapterView.findViewById(R.id.id_textWeakness);
            TextView textLevel = adapterView.findViewById(R.id.id_textLevel);
            SeekBar seekBarLevel = adapterView.findViewById(R.id.id_seekBarLevel);

            final Pokemon pokemon = objects.get(position);

            imageView.setImageResource(pokemon.getImage());
            textName.setText(pokemon.getName());
            textStrength.setText("Strength: " + pokemon.getStrength());
            textWeakness.setText("Weakness: " + pokemon.getWeakness());
            textLevel.setText("Level: " + pokemon.getLevel() + "");
            seekBarLevel.setProgress(pokemon.getLevel());

            seekBarLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    pokemon.setLevel(progress);
                    textLevel.setText("Level: " + pokemon.getLevel());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            return adapterView;

        }

    }

}
