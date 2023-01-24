package com.example.gestionhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gestionhotel.data.entities.Habitacion;
import com.example.gestionhotel.data.entities.Reserva;
import com.example.gestionhotel.data.viewmodels.BookingViewModel;
import com.example.gestionhotel.data.viewmodels.RoomViewModel;
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
    private int num_guests;
    private String observations, room_id, u_id;
    private String[] date;
    private RoomViewModel roomViewModel;
    private BookingViewModel bookingViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        u_id = i.getStringExtra("id");

        System.out.println(u_id);

        mMFragment = new MainMenuFragment();


        ViewModelProvider.AndroidViewModelFactory factory=
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());

        roomViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(RoomViewModel.class);
        bookingViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) factory).get(BookingViewModel.class);

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
        this.num_guests =numGuests;
        this.observations =observations;
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
        this.room_id =id;
        Toast.makeText(this,"Habitacion seleccionada",Toast.LENGTH_SHORT).show();

        makeBooking(date,null,this.u_id,this.room_id,this.num_guests);

        if (id.equals("3")) createRoom();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFLayout, mMFragment).commit();
    }

    /**
     * Method for creating a booking
     *
     * @param fecha_in Arrival date for the booking
     * @param fecha_out Exit date for the booking
     * @param id_usuario User ID for the booking
     * @param id_habitacion Room ID for the booking
     * @param numGuests Number of guests for the booking
     */
    public void makeBooking(String[] fecha_in, String[] fecha_out, String id_usuario, String id_habitacion, int numGuests) {
        String fecha_in_str=fecha_in[0]+'/'+fecha_in[1]+'/'+fecha_in[2];
        String fecha_out_str;
        if (fecha_out!=null) {
            fecha_out_str=fecha_out[0]+'/'+fecha_out[1]+'/'+fecha_out[2];
        } else {
            fecha_out_str=null;
        }

        Reserva booking=new Reserva(fecha_in_str,fecha_out_str,id_usuario, id_habitacion, numGuests);
        bookingViewModel.insertBooking(booking);
    }

    /**
     * Method for creating rooms, normally not used
     */
    public void createRoom() {
        Habitacion h1=new Habitacion("1","Suite para 2 personas en el 5º piso, con buenas vistas al mar",
                200.0, 0);
        Habitacion h2=new Habitacion("2","Habitacion para 4 personas en el 3º piso, acceso facil a la piscina y vistas al mar",
                130.0,0);
        Habitacion h3=new Habitacion("3","Habitacion para 2 personas en el 3º piso, acceso facil a la piscina",
                95.0,0);

        roomViewModel.insertRoom(h1);
        roomViewModel.insertRoom(h2);
        roomViewModel.insertRoom(h3);

    }
}