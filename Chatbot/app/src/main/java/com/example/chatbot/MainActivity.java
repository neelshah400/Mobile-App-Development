package com.example.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String phoneNumber, messageIn, messageOut;
    int stateIn, stateOut;

    int MY_REQUEST;

    BroadcastReceiver receiver;
    SmsManager smsManager;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = "";
        stateIn = 0;
        stateOut = 1;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS}, MY_REQUEST);
        }

    }

    @Override
    protected void onResume() {

        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {

            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    Bundle bundle = intent.getExtras();
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    String format = bundle.getString("format");
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < pdus.length; i++)
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);

                    if (phoneNumber.equals(""))
                        phoneNumber = messages[0].getOriginatingAddress();
                    messageIn = messages[0].getMessageBody();
                    Log.d("TAG", phoneNumber + ": " + messageIn);

                    int delay = (int)(Math.random() * 6000) + 2000;
                    stateIn = detectState();
                    Log.d("TAG", stateIn + "");
                    messageOut = generateMessage();

                    if (!messageOut.equals("")) {
                        handler = new Handler();
                        handler.postDelayed(sendMessage(messageOut), delay);
                        if (stateIn > 0 && stateIn == stateOut)
                            stateOut++;
                    }

                }
            };
            IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(receiver, filter);

        }

    }

    @Override
    protected void onPause() {

        super.onPause();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {

            unregisterReceiver(receiver);

        }

    }

    public Runnable sendMessage(String message) {

        messageOut = message;
        return new Runnable() {
            @Override
            public void run() {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {

                    smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, messageOut, null, null);

                }
            }
        };

    }

    public int detectState() {

        messageIn = messageIn.toLowerCase();

        if (stateIn == 0 && !messageIn.equals(""))
            return 1;
        else if (messageIn.matches(".*(good|okay|fine|bad|well).*"))
            return 2;
        else if (messageIn.matches(".*(why|what)\\??$"))
            return 3;
        else if (messageIn.matches(".*(what|did|am).*(about|problem|wrong|trouble).*\\?.*"))
            return 4;
        else if (messageIn.matches(".*(really|when|do).*\\?.*"))
            return 5;
        else if (messageIn.matches(".*(screw|no|please|beg).*"))
            return 6;
        else if (stateIn >= 6 && messageIn.matches(".*bye.*"))
            return 7;
        else
            return -1;

    }

    public String generateMessage() {

        ArrayList<String> options = new ArrayList<String>();
        if (stateIn == stateOut && stateIn > 0) {
            if (stateOut == 1) {
                options.add("Hey, how are you doing?");
                options.add("Hello, how have you been?");
                options.add("Hey, how are you?");
            }
            else if (stateOut == 2) {
                options.add("I've been meaning to talk to you");
                options.add("We should have a chat");
                options.add("I need to discuss something with you");
                options.add("We need to talk");
            }
            else if (stateOut == 3) {
                options.add("It's about your job");
                options.add("It relates to your performance at work");
                options.add("It's about your job performance");
            }
            else if (stateOut == 4) {
                options.add("I've noticed a decline in your performance recently");
                options.add("It seems like you haven't been on top of your game recently");
                options.add("Your recent performance hasn't been up to par");
                options.add("You haven't been meeting our expectations recently");
            }
            else if (stateOut == 5) {
                options.add("I'm sorry, I have no choice but to fire you");
                options.add("Effective immediately, you are being terminated");
                options.add("Your services are no longer needed");
                options.add("I'm sorry to inform you that your journey with us has reached its end");
            }
            else if (stateOut == 6) {
                options.add("Goodbye, please gather your belongings first thing tomorrow");
                options.add("Bye, please clear your desk out tomorrow morning");
                options.add("Goodbye, wish you the best of luck for the future");
            }
            else if (stateOut > 6) {
                options.add("");
            }
        }
        else {
            options.add("I don't understand, could you respond more clearly?");
            options.add("I'm confused, can you reply again?");
        }
        if (options.size() > 0)
            return options.get((int)(Math.random() * options.size()));
        return "Error";

    }

}
