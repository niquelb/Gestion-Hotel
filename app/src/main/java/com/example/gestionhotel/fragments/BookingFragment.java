package com.example.gestionhotel.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.gestionhotel.R;
import com.google.android.material.button.MaterialButton;

/**
 * Fragment responsible for booking a reservation
 */
public class BookingFragment extends Fragment {

    private BookingFragmentListener listener;
    private int day,month,year;
    private String[] date;
    private boolean isSetDate=false, isRoomSelected=false;

    public interface BookingFragmentListener {
        void onRoomSelectorPressed(int numGuests, String observations, String[] date);
    }

    private String[] onDPPressed(View view) {
        final Calendar c= Calendar.getInstance();
        day =c.get(Calendar.DAY_OF_MONTH);
        month =c.get(Calendar.MONTH);
        year =c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), (view1, yr, monthOfYear, dayOfMonth) -> {
            day=dayOfMonth;
            month=monthOfYear;
            year=yr;
        }, day, month, year);
        datePickerDialog.show();
        return new String[]{String.valueOf(day), String.valueOf(month), String.valueOf(year)};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_booking, container, false);

        ImageButton datePickerIB=(ImageButton) v.findViewById(R.id.datePickerIB);
        EditText numGuests=(EditText) v.findViewById(R.id.numGuestsET);
        EditText observations=(EditText) v.findViewById(R.id.observationsET);
        MaterialButton selectRoomBtn=(MaterialButton) v.findViewById(R.id.selectRoomBtn);

        datePickerIB.setOnClickListener(view -> {
            isSetDate=true;
            date=onDPPressed(v);
        });

        selectRoomBtn.setOnClickListener(view -> {
            listener.onRoomSelectorPressed(Integer.parseInt(numGuests.getText().toString()),observations.getText().toString(),date);
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
     * @deprecated
     * @param day Day of the booking
     * @param month Month of the booking
     * @param year Year of the booking
     */
    public void setTimeDateParams(int day, int month, int year) {
        this.day=day;
        this.month=month;
        this.year=year;
    }
}