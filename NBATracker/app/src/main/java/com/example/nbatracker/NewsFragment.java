package com.example.nbatracker;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NewsFragment extends Fragment {

    private SharedViewModel model;

    TabLayout tabLayout;
    ListView listView;
    ArticleAdapter articleAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_news, container, false);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        tabLayout = root.findViewById(R.id.id_tabLayout);
        listView = root.findViewById(R.id.id_listView);

        tabLayout.addTab(tabLayout.newTab().setCustomView(getImage("https://icons.iconarchive.com/icons/blackvariant/button-ui-requests-13/512/NBA-icon.png")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(getImage("https://iconsplace.com/wp-content/uploads/_icons/ffa500/256/png/rating-star-icon-11-256.png")));
        model.getTeams().observe(getViewLifecycleOwner(), new Observer<ArrayList<Team>>() {
            @Override
            public void onChanged(ArrayList<Team> teams) {
                for (Team team : teams)
                    tabLayout.addTab(tabLayout.newTab().setCustomView(getImage("https://www.nba.com/.element/img/1.0/teamsites/logos/teamlogos_500x500/" + team.getTricode().toLowerCase() + ".png")));
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("SHAH", "yes" + tabLayout.getSelectedTabPosition());
                model.setArticles(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected( TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        model.getArticles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(ArrayList<Article> articles) {
                Log.d("SHAH", "test");
                int pos = listView.getFirstVisiblePosition();
                View v = listView.getChildAt(0);
                int offset = (v == null) ? 0 : v.getTop();
                articleAdapter = new ArticleAdapter(getContext(), R.layout.adapter_article, articles);
                listView.setAdapter(articleAdapter);
                listView.setSelectionFromTop(pos, offset);
            }
        });

        return root;

    }

    public View getImage(String url) {
        ImageView imageView = new ImageView(getContext());
        TabLayout.LayoutParams params = new TabLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(params);
        Picasso.get().load(url).into(imageView);
        return imageView;
    }

}
