package com.example.gestionhotel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

/**
 * Fragment responsible for the date-picking of the booking,
 * accessed from BookingFragment
 */
public class DatePickerFragment extends Fragment {

    private DatePickerFragmentListener listener;

    public interface DatePickerFragmentListener {
        void onDPSubmitted(int dia, int mes, int ano, int hora, int mins);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_date_picker, container, false);

        MaterialButton submit=(MaterialButton) v.findViewById(R.id.submitTimePickerBtn);
        MaterialButton fechaBtn=(MaterialButton) v.findViewById(R.id.fechaBtn);
        MaterialButton horaBtn=(MaterialButton) v.findViewById(R.id.horaBtn);
        TextView fechaTV=(TextView) v.findViewById(R.id.fechaTV);
        TextView horaTV=(TextView) v.findViewById(R.id.horaTV);

        final boolean[] isSetDate = {false};
        final boolean[] isSetTime = {false};

        final int[] day = new int[1];
        final int[] month = new int[1];
        final int[] year = new int[1];

        final int[] hour = new int[1];
        final int[] mins = new int[1];

        fechaBtn.setOnClickListener(view -> {
            isSetDate[0] =true;

            final Calendar c= Calendar.getInstance();
            day[0] =c.get(Calendar.DAY_OF_MONTH);
            month[0] =c.get(Calendar.MONTH);
            year[0] =c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), (view1, yr, monthOfYear, dayOfMonth) -> {
                fechaTV.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                day[0] =dayOfMonth;
                month[0] =monthOfYear;
                year[0] =yr;
            }, day[0], month[0], year[0]);
            datePickerDialog.show();
        });

        horaBtn.setOnClickListener(view -> {
            isSetTime[0] =true;

            final Calendar c= Calendar.getInstance();
            hour[0] =c.get(Calendar.HOUR_OF_DAY);
            mins[0] =c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), (view12, hourOfDay, minute) -> {
                horaTV.setText(hourOfDay+":"+minute);
                hour[0] =hourOfDay;
                mins[0] =minute;
            }, hour[0], mins[0],false);
            timePickerDialog.show();
        });

        submit.setOnClickListener(view -> {
            if (isSetDate[0] && isSetTime[0]) {
                listener.onDPSubmitted(day[0],month[0],year[0],hour[0],mins[0]);
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DatePickerFragmentListener){
            listener=(DatePickerFragmentListener) context;
        } else throw new RuntimeException(context +" must implement BookingFragment.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}