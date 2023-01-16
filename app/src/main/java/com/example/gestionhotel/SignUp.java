package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.gestionhotel.fragments.SignUpFragment;

/**
 * Activity responsible for containing the signup fragments,
 * accessible from Login activity
 */
public class SignUp extends AppCompatActivity implements SignUpFragment.SignUpFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public void onSubmit1(String email, String password) {
        Toast.makeText(this, "Email: "+email+" Password: "+password, Toast.LENGTH_LONG).show();
    }
}