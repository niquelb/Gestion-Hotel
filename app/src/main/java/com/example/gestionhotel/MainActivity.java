package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.MainMenuFragmentListener {

    private MainMenuFragment mMFragment;
    private BookingFragment bookingFragment;

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
        if (input.toString().equals("book")) {

            bookingFragment=new BookingFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFLayout, bookingFragment).commit();
        } else if (input.toString().equals("about")) {
            // Reemplazar mainFLayout por el fragment correspondiente
        } else if (input.toString().equals("location")) {
            // Reemplazar mainFLayout por el fragment correspondiente
        } else throw new RuntimeException("onInputSent Exception on MainActivity: couldn't resolve input origin.");

        Toast.makeText(MainActivity.this,input,Toast.LENGTH_SHORT).show();
    }
}