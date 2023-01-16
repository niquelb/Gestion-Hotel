package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionhotel.data.viewmodels.UsuarioViewModel;
import com.google.android.material.button.MaterialButton;

/**
 * Activity for logging into the app
 */
public class Login extends AppCompatActivity {

    private UsuarioViewModel uViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewModelProvider.AndroidViewModelFactory factory=
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        uViewModel=new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) factory).get(UsuarioViewModel.class);


        EditText uName=(EditText) findViewById(R.id.uNameET);
        EditText passwd=(EditText) findViewById(R.id.pwdET);
        TextView noAccount=(TextView) findViewById(R.id.noAccountTV);

        MaterialButton loginBtn=(MaterialButton) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(view -> {
            if (uName.getText().toString().equals("admin") && passwd.getText().toString().equals("admin")){
                Intent i=new Intent(Login.this, MainActivity.class);
                startActivity(i);
                finish();
            } else Toast.makeText(Login.this,"Login Incorrecto",Toast.LENGTH_LONG).show();
        });

        noAccount.setOnClickListener(view -> {
            Intent i=new Intent(Login.this, SignUp.class);
            startActivity(i);
        } );
    }
}