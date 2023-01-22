package com.example.gestionhotel.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gestionhotel.data.daos.HabitacionDao;
import com.example.gestionhotel.data.daos.ReservaDao;
import com.example.gestionhotel.data.daos.UsuarioDao;
import com.example.gestionhotel.data.entities.Habitacion;
import com.example.gestionhotel.data.entities.Reserva;
import com.example.gestionhotel.data.entities.Usuario;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Habitacion.class, Usuario.class, Reserva.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    public abstract HabitacionDao habitacionDao();
    public abstract UsuarioDao usuarioDao();
    public abstract ReservaDao reservaDao();

    private static final String DATABASE_NAME = "hoteldb";

    private static AppDB INSTANCE;

    private static final int THREADS = 4;
    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public static AppDB getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = androidx.room.Room.databaseBuilder(
                                    context.getApplicationContext(), AppDB.class,
                                    DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
