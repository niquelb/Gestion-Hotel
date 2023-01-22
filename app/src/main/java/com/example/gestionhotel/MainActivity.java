package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gestionhotel.data.entities.Habitacion;
import com.example.gestionhotel.data.viewmodels.RoomViewModel;
import com.example.gestionhotel.data.viewmodels.UserViewModel;
import com.example.gestionhotel.fragments.BookingFragment;
import com.example.gestionhotel.fragments.DatePickerFragment;
import com.example.gestionhotel.fragments.MainMenuFragment;
import com.example.gestionhotel.fragments.RoomSelectorFragment;

/**
 * Main Class of the app
 */
public class MainActivity extends AppCompatActivity implements MainMenuFragment.MainMenuFragmentListener,
        BookingFragment.BookingFragmentListener, RoomSelectorFragment.RoomSelectorListener {

    private MainMenuFragment mMFragment;
    private BookingFragment bookingFragment;
    private RoomSelectorFragment roomSelectorFragment;
    private DatePickerFragment datePickerFragment;
    private int numGuests;
    private String addObservations, roomId;
    private String[] date;
    private RoomViewModel roomViewModel;


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
        this.numGuests=numGuests;
        this.addObservations=observations;
        this.date=date;
        roomSelectorFragment= new RoomSelectorFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, roomSelectorFragment).commit();
    }

    /**
     * Gets executed inside RoomSelectorFragment when the user selects a room
     *
     * @param id id of the selected room
     */
    @Override
    public void onRoomSelected(String id) {
        this.roomId=id;
        Toast.makeText(this,"Habitacion seleccionada",Toast.LENGTH_SHORT).show();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, mMFragment).commit();
    }
    
    public void makeBooking() {}

    /**
     * Method for creating rooms, normally not used
     */
    public void createRoom() {
        ViewModelProvider.AndroidViewModelFactory factory=
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        roomViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(RoomViewModel.class);

        Habitacion h1=new Habitacion("1","Suite para 2 personas en el 5º piso, con buenas vistas al mar",
                200.0, 0);
        Habitacion h2=new Habitacion("2","Habitacion para 4 personas en el 3º piso, acceso facil a la piscina y vistas al mar",
                130.0,0);
        Habitacion h3=new Habitacion("3","Habitacion para 2 personas en el 3º piso, acceso facil a la piscina",
                95.0,0);

        roomViewModel.guardarHabitacion(h1);
        roomViewModel.guardarHabitacion(h2);
        roomViewModel.guardarHabitacion(h3);

    }
}