package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionhotel.data.viewmodels.UserViewModel;
import com.google.android.material.button.MaterialButton;

/**
 * Activity for logging into the app
 */
public class Login extends AppCompatActivity {

    private UserViewModel uViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewModelProvider.AndroidViewModelFactory factory=
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        uViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(UserViewModel.class);


        EditText uName=(EditText) findViewById(R.id.uNameET);
        EditText passwd=(EditText) findViewById(R.id.pwdET);
        TextView noAccount=(TextView) findViewById(R.id.noAccountTV);

        MaterialButton loginBtn=(MaterialButton) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(view -> {
            uViewModel.getUsuario(uName.getText().toString(),passwd.getText().toString())
                    .observe(this,
                            checkUser -> {
                                if (checkUser!=null) {
                                    Intent i=new Intent(Login.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else Toast.makeText(Login.this,"Login Incorrecto",Toast.LENGTH_LONG).show();
                            });
        });

        noAccount.setOnClickListener(view -> {
            Intent i=new Intent(Login.this, SignUp.class);
            startActivity(i);
        } );
    }
}