package com.example.broadcastpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
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
        myReceiver = new AirplaneModeMonitor();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(myReceiver, intentFilter);

    }

    @Override
    protected void onPause() {

        super.onPause();
        unregisterReceiver(myReceiver);

    }

    public class AirplaneModeMonitor extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "Airplane Mode Changed", Toast.LENGTH_SHORT).show();
            int status = Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0);
            if (status == 0)
                textView.setText("Airplane Mode Off");
            if (status == 1)
                textView.setText("Airplane Mode On");

        }

    }

}
