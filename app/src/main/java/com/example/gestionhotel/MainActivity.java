package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gestionhotel.fragments.BookingFragment;
import com.example.gestionhotel.fragments.DatePickerFragment;
import com.example.gestionhotel.fragments.MainMenuFragment;

/**
 * Main Class of the app
 */
public class MainActivity extends AppCompatActivity implements MainMenuFragment.MainMenuFragmentListener,
        BookingFragment.BookingFragmentListener {

    private MainMenuFragment mMFragment;
    private BookingFragment bookingFragment;
    private DatePickerFragment datePickerFragment;
    private int numGuests;
    private String addObservations;
    private String[] date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        final String ID = i.getStringExtra("id");

        System.out.println(ID);

        mMFragment = new MainMenuFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, mMFragment).commit();
    }

    /**
     * Method responsible for switching between the MainMenuFragment and the other fragments
     *
     * @param input fragment to be switched to
     */
    @Override
    public void onInputSent(CharSequence input) {
        if (input.toString().equals("book")) {

            bookingFragment = new BookingFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFLayout, bookingFragment).commit();
        } else if (input.toString().equals("about")) {
            //TODO Reemplazar mainFLayout por el fragment correspondiente
        } else if (input.toString().equals("location")) {
            //TODO Reemplazar mainFLayout por el fragment correspondiente
        } else
            throw new RuntimeException("onInputSent Exception on MainActivity: couldn't resolve input origin.");

    }


    /**
     * Gets executed inside BookingFragment when the user clicks the room selector button
     *
     * @param numGuests    Number of guests for the booking
     * @param observations Additional observations
     * @param date         Array with the day, month and year of the booking
     */
    @Override
    public void onRoomSelectorPressed(int numGuests, String observations, String[] date) {
//        Toast.makeText(this, "Booking Submitted", Toast.LENGTH_LONG);
        this.numGuests=numGuests;
        this.addObservations=observations;
        this.date=date;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, mMFragment).commit();
    }

}