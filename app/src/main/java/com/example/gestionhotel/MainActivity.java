package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText uName=(EditText) findViewById(R.id.uNameET);
        EditText passwd=(EditText) findViewById(R.id.pwdET);

        MaterialButton loginBtn=(MaterialButton) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uName.getText().toString().equals("admin") && passwd.getText().toString().equals("admin"))
                    Toast.makeText(MainActivity.this,"Login Correcto",Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this,"Login Incorrecto",Toast.LENGTH_LONG).show();
            }
        });
    }
}