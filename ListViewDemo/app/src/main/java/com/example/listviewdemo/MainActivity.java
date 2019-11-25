package com.example.listviewdemo;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.id_listView);
        arrayList = new ArrayList<String>();
        arrayList.add("Bob");
        arrayList.add("Bill");
        arrayList.add("Jim");
        arrayList.add("2");
        arrayList.add("6");
        arrayList.add("John");
        arrayList.add("Jim");
        arrayList.add("Rob");
        arrayList.add("dog");
        arrayList.add("cat");
        arrayList.add("10");
        arrayList.add("Steve");
        arrayList.add("Jen");

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, arrayList);
        listView.setAdapter(customAdapter);

    }

    public class CustomAdapter extends ArrayAdapter<String> {

        Context context;
        int xmlResource;
        List<String> list;


        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {

            super(context, resource, objects);
            this.context = context;
            xmlResource = resource;
            list = objects;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = layoutInflater.inflate(xmlResource, null);
            TextView textView = adapterView.findViewById(R.id.id_adapterText);
            Button button = adapterView.findViewById(R.id.id_adapterButton);

            textView.setText("Name: " + list.get(position));
            button.setText("Position: " + position);

            return adapterView;

        }

    }

}
