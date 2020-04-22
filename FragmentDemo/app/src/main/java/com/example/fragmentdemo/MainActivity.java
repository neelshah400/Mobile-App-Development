package com.example.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements BottomFragment.SendInfo {

    Button buttonReplace;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    BottomFragment bottomFragment;
    TopFragment topFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonReplace = findViewById(R.id.id_buttonReplace);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        bottomFragment = new BottomFragment();
        topFragment = new TopFragment();

        fragmentTransaction.add(R.id.id_bottom, bottomFragment);
        fragmentTransaction.add(R.id.id_top, topFragment);

        fragmentTransaction.commit();

        buttonReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = fragmentManager.beginTransaction();

                bottomFragment = new BottomFragment();
                topFragment = new TopFragment();

                if (fragmentManager.findFragmentById(R.id.id_top) instanceof TopFragment) {
                    fragmentTransaction.replace(R.id.id_top, bottomFragment);
                    fragmentTransaction.replace(R.id.id_bottom, topFragment);
                }
                else {
                    fragmentTransaction.replace(R.id.id_top, topFragment);
                    fragmentTransaction.replace(R.id.id_bottom, bottomFragment);
                }
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public void update(String str) {
        buttonReplace.setText(str);
    }

}
