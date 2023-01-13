package com.example.gestionhotel.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gestionhotel.data.entities.Habitacion;

import java.util.List;

@Dao
public
interface HabitacionDao {
    @Insert
    public void insert(Habitacion habitacion);

    @Insert
    public void insertBothUsers(Habitacion hab1, Habitacion hab2);

    @Update
    public void update(Habitacion habitacion);

    @Delete
    public void delete(Habitacion habitacion);

    @Query("SELECT * FROM habitaciones ORDER BY id")
    public LiveData<List<Habitacion>> getAll();

    @Query("SELECT * FROM habitaciones WHERE id = :miId")
    public LiveData<Habitacion> getOne(String miId);

    @Query("SELECT * FROM habitaciones WHERE precio BETWEEN :minPrecio AND :maxPrecio")
    public LiveData<List<Habitacion>> getAllPrices(int minPrecio, int maxPrecio);

    @Query("SELECT * FROM habitaciones WHERE descrip LIKE :search ")
    public LiveData<List<Habitacion>> findHabitacion(String search);

}
