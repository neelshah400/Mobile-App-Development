package com.example.nbatracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GamesFragment extends Fragment {

    private static SharedViewModel model;

    static EditText editDate;

    ListView listView;
    GameAdapter gameAdapter;

    ConstraintLayout layout;
    WebView webView;
    ImageButton buttonBack, buttonShare;
    Button buttonOpen;

    Game game;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_games, container, false);
        model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        editDate = root.findViewById(R.id.id_editDate);
        listView = root.findViewById(R.id.id_listView);

        layout = root.findViewById(R.id.id_layout);
        webView = root.findViewById(R.id.id_webView);
        buttonBack = root.findViewById(R.id.id_buttonBack);
        buttonShare = root.findViewById(R.id.id_buttonShare);
        buttonOpen = root.findViewById(R.id.id_buttonOpen);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDomStorageEnabled(true);

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        editDate.setText(dateFormat.format(date));

        editDate.setInputType(InputType.TYPE_NULL);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "datePicker");
            }
        });

        model.getGames().observe(getViewLifecycleOwner(), new Observer<ArrayList<Game>>() {
            @Override
            public void onChanged(ArrayList<Game> games) {
                Log.d("SHAH", games + "");
                gameAdapter = new GameAdapter(getContext(), R.layout.adapter_game, games);
                listView.setAdapter(gameAdapter);
            }
        });

        dateFormat = new SimpleDateFormat("yyyyMMdd");
        model.setGames(dateFormat.format(date));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                game = model.getGames().getValue().get(position);
                webView.loadUrl(game.getGameURL());
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
                intent.putExtra(Intent.EXTRA_TEXT, game.getGameURL());
                startActivity(intent.createChooser(intent, "Share via"));
            }
        });

        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(game.getGameURL()));
                startActivity(intent);
            }
        });

        return root;

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            editDate.setText((month + 1) + "/" + dayOfMonth + "/" + year);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String date = dateFormat.format(calendar.getTime());
            model.setGames(date);
        }

    }

}
