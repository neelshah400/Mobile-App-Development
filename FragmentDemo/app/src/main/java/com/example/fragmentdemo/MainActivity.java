package com.example.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonReplace;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    BottonFragment bottonFragment;
    TopFragment topFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonReplace = findViewById(R.id.id_buttonReplace);

        fragmentManager = getSupportFragmentManager();

        //Begin first transaction
        fragmentTransaction = fragmentManager.beginTransaction();

        //Create bottom fragment and add to layout on bottom of XML
        bottonFragment = new BottonFragment();
        fragmentTransaction.add(R.id.id_bottom, bottonFragment);

        //commit the transaction (end)
        fragmentTransaction.commit();

        buttonReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottonFragment = new BottonFragment();
                topFragment = new TopFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                if (fragmentManager.findFragmentById(R.id.id_top) instanceof TopFragment) {
                    fragmentTransaction.replace(R.id.id_top, bottonFragment);
                    fragmentTransaction.replace(R.id.id_bottom, topFragment);
                }
                else {
                    fragmentTransaction.replace(R.id.id_top, topFragment);
                    fragmentTransaction.replace(R.id.id_bottom, bottonFragment);
                }
                fragmentTransaction.commit();

            }
        });

    }

}
