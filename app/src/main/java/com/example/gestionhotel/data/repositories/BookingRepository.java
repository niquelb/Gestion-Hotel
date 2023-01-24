package com.example.gestionhotel.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.gestionhotel.data.AppDB;
import com.example.gestionhotel.data.daos.ReservaDao;
import com.example.gestionhotel.data.entities.Reserva;

import java.util.List;

public class BookingRepository {

    private final ReservaDao reservaDao;

    public BookingRepository(Context context) {
        AppDB db=AppDB.getInstance(context);
        reservaDao=db.reservaDao();
    }

    public void insert(Reserva reserva) {
        AppDB.dbExecutor.execute(
                ()->reservaDao.insert(reserva)
        );
    }

    public void update(Reserva reserva) {
        AppDB.dbExecutor.execute(
                ()->reservaDao.insert(reserva)
        );
    }

    public void delete(Reserva reserva) {
        AppDB.dbExecutor.execute(
                ()->reservaDao.delete(reserva)
        );
    }

    public LiveData<List<Reserva>> getAll() {
        return reservaDao.getAll();
    }

    public LiveData<Reserva> getById(String id) {
        return reservaDao.getById(id);
    }

    public LiveData<List<Reserva>> getByUser(String uId) {
        return reservaDao.getByUser(uId);
    }

    public LiveData<List<Reserva>> getByRoom(String hId) {
        return reservaDao.getByRoom(hId);
    }
}
