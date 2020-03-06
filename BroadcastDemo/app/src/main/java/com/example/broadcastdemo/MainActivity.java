package com.example.broadcastdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    BroadcastReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.id_textView);

    }

    @Override
    protected void onResume() {

        super.onResume();
        myReceiver = new BatteryMonitor();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(myReceiver, intentFilter);

    }

    @Override
    protected void onPause() {

        super.onPause();
        unregisterReceiver(myReceiver);

    }

    public class BatteryMonitor extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "Battery Changed", Toast.LENGTH_SHORT).show();
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -99);

            if (status == -1)
                textView.setText("Error");
            if (status == 5)
                textView.setText("Full Charge");
            if (status == 2)
                textView.setText("Charging");
            if (status == 3)
                textView.setText("Not Charging");

            Log.d("TAG", "Status is " + status);

        }

    }

}
