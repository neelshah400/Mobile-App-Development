package com.example.dialogdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonShowDialog, buttonLogin;
    EditText editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonShowDialog = findViewById(R.id.id_buttonShowDialog);

        buttonShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_login, null);

                editEmail = view.findViewById(R.id.id_editEmail);
                editPassword = view.findViewById(R.id.id_editPassword);
                buttonLogin = view.findViewById(R.id.id_buttonLogin);

                buttonLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!editEmail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty())
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

}
