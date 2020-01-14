package com.example.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WeatherAdapter extends ArrayAdapter<Weather> {

    Context context;
    int resource;
    List<Weather> objects;
    Weather weather;

    TextView textDateTime, textDescription, textTempMax, textTempMin;
    ImageView imageWeather;

    public WeatherAdapter(@NonNull Context context, int resource, @NonNull List<Weather> objects) {

        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View adapterView = layoutInflater.inflate(resource, null);

        textDateTime = adapterView.findViewById(R.id.id_textDateTime);
        textDescription = adapterView.findViewById(R.id.id_textDescription);
        textTempMax = adapterView.findViewById(R.id.id_textTempMax);
        textTempMin = adapterView.findViewById(R.id.id_textTempMin);
        imageWeather = adapterView.findViewById(R.id.id_imageWeather);

        weather = objects.get(position);

        textDateTime.setText(weather.getFormattedDate("EEE, MMM DD - h a"));
        textDescription.setText(weather.getDescription());
        textTempMax.setText(weather.getTempMax() + "°");
        textTempMin.setText(weather.getTempMin() + "°");
        imageWeather.setImageResource(weather.getImage());

        return adapterView;

    }

}
