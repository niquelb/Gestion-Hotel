package com.example.gestionhotel.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gestionhotel.data.entities.Habitacion;
import com.example.gestionhotel.data.repositories.RoomRepository;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {
    private final RoomRepository hRepo;
    private final LiveData<List<Habitacion>> listadoHabitaciones;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        this.hRepo = new RoomRepository(application);
        this.listadoHabitaciones = hRepo.getAllR();
    }

    public LiveData<List<Habitacion>> obtenerHabitaciones(){
        return listadoHabitaciones;
    }

    public LiveData<Habitacion> obtenerHabitacion(String mId){
        return hRepo.getOne(mId);
    }

    public void guardarHabitacion(Habitacion habitacion){
        hRepo.insert(habitacion);
    }
    public void eliminarHabitacion(Habitacion habitacion){
        hRepo.delete(habitacion);
    }
}
