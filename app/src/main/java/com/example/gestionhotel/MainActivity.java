package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.MainMenuFragmentListener {

    private MainMenuFragment mMFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMFragment=new MainMenuFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, mMFragment).commit();
    }

    @Override
    public void onInputSent(CharSequence input) {
        Toast.makeText(MainActivity.this,input,Toast.LENGTH_LONG).show();
    }
}