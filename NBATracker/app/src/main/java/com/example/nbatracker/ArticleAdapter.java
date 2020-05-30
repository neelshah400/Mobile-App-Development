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

public class ArticleAdapter extends ArrayAdapter<Article> {

    Context context;
    int resource;
    List<Article> objects;
    Article article;

    TextView textTitle;
    ImageView imageView;

    public ArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
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

        textTitle = adapterView.findViewById(R.id.id_textTitle);
        imageView = adapterView.findViewById(R.id.id_imageView);

        article = objects.get(position);
        textTitle.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage()).into(imageView);

        return adapterView;

    }

}
