package com.example.gestionhotel.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gestionhotel.data.daos.HabitacionDao;
import com.example.gestionhotel.data.daos.UsuarioDao;
import com.example.gestionhotel.data.entities.Habitacion;
import com.example.gestionhotel.data.entities.Usuario;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Habitacion.class, Usuario.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    public abstract HabitacionDao habitacionDao();
    public abstract UsuarioDao usuarioDao();

    private static final String DATABASE_NAME = "hoteldb";

    private static AppDB INSTANCE;

    private static final int THREADS = 4;
    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public static AppDB getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(), AppDB.class,
                                    DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
