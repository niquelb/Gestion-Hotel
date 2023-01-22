package com.example.gestionhotel.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gestionhotel.data.entities.Reserva;

import java.util.List;

@Dao
public interface ReservaDao {

    @Insert
    void insert(Reserva reserva);

    @Update
    void update(Reserva reserva);

    @Delete
    void delete(Reserva reserva);

    @Query("SELECT * FROM reservas ORDER BY id")
    LiveData<List<Reserva>> getAll();

    @Query("SELECT * FROM reservas WHERE id LIKE :rId")
    LiveData<Reserva> getById(String rId);

    @Query("SELECT * FROM reservas WHERE id_usuario LIKE :uId")
    LiveData<List<Reserva>> getByUser(String uId);

    @Query("SELECT * FROM reservas WHERE id_habitacion LIKE :hId")
    LiveData<List<Reserva>> getByRoom(String hId);
}
