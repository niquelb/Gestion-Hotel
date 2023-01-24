package com.example.gestionhotel.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.gestionhotel.data.AppDB;
import com.example.gestionhotel.data.daos.HabitacionDao;
import com.example.gestionhotel.data.entities.Habitacion;

import java.util.List;

public class RoomRepository {

    private final HabitacionDao habitacionDao;

    private final LiveData<List<Habitacion>> listado_habitaciones;

    public RoomRepository(Context context){
        AppDB db = AppDB.getInstance(context);
        habitacionDao = db.habitacionDao();
        listado_habitaciones = habitacionDao.getAll();
    }

    public void insert (Habitacion habitacion){
        AppDB.dbExecutor.execute(
                ()->habitacionDao.insert(habitacion)
        );
    }
    public LiveData<List<Habitacion>> getAllR(){
        return listado_habitaciones;
    }

    public void delete (Habitacion habitacion){
        AppDB.dbExecutor.execute(
                ()->habitacionDao.delete(habitacion)
        );
    }

    public LiveData<Habitacion> getOne(String miId){
        return habitacionDao.getOne(miId);
    }

}
