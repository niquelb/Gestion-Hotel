package com.example.gestionhotel.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gestionhotel.R;
import com.google.android.material.button.MaterialButton;

/**
 * Fragment responsible for booking a reservation
 */
public class BookingFragment extends Fragment {

    private BookingFragmentListener listener;
    private int day,month,year,hour,mins;

    public interface BookingFragmentListener {
        void onDPPressed();
        void onBookingSubmitted(int numGuests, String observations, int day, int month, int year, int hour, int mins);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_booking, container, false);

        ImageButton datePickerIB=(ImageButton) v.findViewById(R.id.datePickerIB);
        EditText numGuests=(EditText) v.findViewById(R.id.numGuestsET);
        EditText observations=(EditText) v.findViewById(R.id.observationsET);
        MaterialButton makeBookingBtn=(MaterialButton) v.findViewById(R.id.makeBookingBtn);

        datePickerIB.setOnClickListener(view -> {
            listener.onDPPressed();

        });

        makeBookingBtn.setOnClickListener(view -> {
            listener.onBookingSubmitted(Integer.parseInt(numGuests.getText().toString()),observations.getText().toString(),day,month,year,hour,mins);
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BookingFragmentListener){
            listener=(BookingFragmentListener) context;
        } else throw new RuntimeException(context +" must implement BookingFragment.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    /**
     * Method for setting the date and time for the reservation,
     * accessed from DatePickerFragment
     * @param day Day of the booking
     * @param month Month of the booking
     * @param year Year of the booking
     * @param hour Hour of the booking
     * @param mins Minute of the booking
     */
    public void setTimeDateParams(int day, int month, int year, int hour, int mins) {
        this.day=day;
        this.month=month;
        this.year=year;
        this.hour=hour;
        this.mins=mins;
    }
}