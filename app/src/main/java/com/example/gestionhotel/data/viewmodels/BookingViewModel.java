package com.example.gestionhotel.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gestionhotel.data.entities.Reserva;
import com.example.gestionhotel.data.repositories.BookingRepository;

import java.util.List;

public class BookingViewModel extends AndroidViewModel {
    private final BookingRepository bookingRepository;

    public BookingViewModel(@NonNull Application application) {
        super(application);
        this.bookingRepository=new BookingRepository(application);
    }

    public LiveData<Reserva> getById(String id) {
        return bookingRepository.getById(id);
    }

    public LiveData<List<Reserva>> getByUser(String uId) {
        return bookingRepository.getByUser(uId);
    }

    public LiveData<List<Reserva>> getByRoom(String hId) {
        return bookingRepository.getByRoom(hId);
    }

    public LiveData<List<Reserva>> getAll() {
        return bookingRepository.getAll();
    }

    public void insertBooking(Reserva booking) {
        bookingRepository.insert(booking);
    }

    public void updateBooking(Reserva booking) {
        bookingRepository.update(booking);
    }

    public void deleteBooking(Reserva booking) {
        bookingRepository.delete(booking);
    }
}
