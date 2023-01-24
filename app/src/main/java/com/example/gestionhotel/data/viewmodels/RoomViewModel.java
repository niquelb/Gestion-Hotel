package com.example.gestionhotel.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gestionhotel.data.entities.Habitacion;
import com.example.gestionhotel.data.repositories.RoomRepository;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {
    private final RoomRepository roomRepository;
    private final LiveData<List<Habitacion>> listRooms;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        this.roomRepository = new RoomRepository(application);
        this.listRooms = roomRepository.getAllR();
    }

    public LiveData<List<Habitacion>> getAllRooms(){
        return listRooms;
    }

    public LiveData<Habitacion> getRoom(String mId){
        return roomRepository.getOne(mId);
    }

    public void insertRoom(Habitacion habitacion){
        roomRepository.insert(habitacion);
    }
    public void deleteRoom(Habitacion habitacion){
        roomRepository.delete(habitacion);
    }
}
