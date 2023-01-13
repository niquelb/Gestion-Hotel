package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

/**
 * Main Class of the app
 */
public class MainActivity extends AppCompatActivity implements MainMenuFragment.MainMenuFragmentListener,
        BookingFragment.BookingFragmentListener, DatePickerFragment.DatePickerFragmentListener {

    private MainMenuFragment mMFragment;
    private BookingFragment bookingFragment;
    private DatePickerFragment datePickerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMFragment=new MainMenuFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, mMFragment).commit();
    }

    /**
     * Method responsible for switching between the MainMenuFragment and the other fragments
     * @param input fragment to be switched to
     */
    @Override
    public void onInputSent(CharSequence input) {
        if (input.toString().equals("book")) {

            bookingFragment=new BookingFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFLayout, bookingFragment).commit();
        } else if (input.toString().equals("about")) {
            //TODO Reemplazar mainFLayout por el fragment correspondiente
        } else if (input.toString().equals("location")) {
            //TODO Reemplazar mainFLayout por el fragment correspondiente
        } else throw new RuntimeException("onInputSent Exception on MainActivity: couldn't resolve input origin.");

    }

    /**
     * Gets executed inside BookingFragment when the user clicks the date selector button,
     * switches to DatePickerFragment
     */
    @Override
    public void onDPPressed() {
        datePickerFragment=new DatePickerFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, datePickerFragment).commit();
    }

    /**
     * Gets executed inside BookingFragment when the user clicks the submit button
     * @param numGuests Number of guests for the booking
     * @param observations Additional observations
     * @param day Day of the booking
     * @param month Month of the booking
     * @param year Year of the booking
     * @param hour Hour of the booking
     * @param mins Minute of the booking
     */
    @Override
    public void onBookingSubmitted(int numGuests, String observations, int day, int month, int year, int hour, int mins) {
        Toast.makeText(this,"Booking Submitted",Toast.LENGTH_LONG);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, mMFragment).commit();
    }

    /**
     * Gets executed inside the DatePickerFragment when the user clicks the submit button
     * @param day Day of the booking
     * @param month Month of the booking
     * @param year Year of the booking
     * @param hour Hour of the booking
     * @param mins Minute of the booking
     */
    @Override
    public void onDPSubmitted(int day, int month, int year, int hour, int mins) {

        bookingFragment.setTimeDateParams(day,month,year,hour,mins);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, bookingFragment).commit();
    }
}