package com.example.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textPhoneNumber, textMessage;
    EditText editPhoneNumber, editMessage;
    Button buttonSend;

    String phoneNumber, message;

    int MY_REQUEST;
    SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textPhoneNumber = findViewById(R.id.id_textPhoneNumber);
        textMessage = findViewById(R.id.id_textMessage);
        editPhoneNumber = findViewById(R.id.id_editPhoneNumber);
        editMessage = findViewById(R.id.id_editMessage);
        buttonSend = findViewById(R.id.id_buttonSend);

        editPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneNumber = s + "";
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                message = s + "";
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS}, MY_REQUEST);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {

            smsManager = SmsManager.getDefault();
            buttonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    Log.d("TAG", "Sent");
                }
            });

        }

    }

}
