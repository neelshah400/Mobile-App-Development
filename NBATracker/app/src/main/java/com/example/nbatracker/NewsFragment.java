package com.example.nbatracker;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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

    ConstraintLayout layout;
    WebView webView;
    ImageButton buttonBack, buttonShare;
    Button buttonOpen;

    Article article;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_news, container, false);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        tabLayout = root.findViewById(R.id.id_tabLayout);
        listView = root.findViewById(R.id.id_listView);

        layout = root.findViewById(R.id.id_layout);
        webView = root.findViewById(R.id.id_webView);
        buttonBack = root.findViewById(R.id.id_buttonBack);
        buttonShare = root.findViewById(R.id.id_buttonShare);
        buttonOpen = root.findViewById(R.id.id_buttonOpen);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());

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
                model.setArticles(tabLayout.getSelectedTabPosition());
                listView.setSelection(0);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                article = model.getArticles().getValue().get(position);
                webView.loadUrl(article.getUrl());
                layout.setVisibility(View.VISIBLE);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
            }
        });

        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
                startActivity(intent.createChooser(intent, "Share via"));
            }
        });

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(article.getUrl()));
                startActivity(intent);
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
