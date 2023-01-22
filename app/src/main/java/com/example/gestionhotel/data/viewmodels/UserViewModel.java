package com.example.gestionhotel.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gestionhotel.data.entities.Usuario;
import com.example.gestionhotel.data.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(new Application());
        this.userRepository = new UserRepository(application);
    }

    public LiveData<Usuario> getUser(String email, String password) {
        return userRepository.getOne(email, password);
    }

    public LiveData<Usuario> getUser(String email) {
        return userRepository.getOne(email);
    }

    public void updateUser(Usuario usuario) {
        userRepository.update(usuario);
    }

    public void insertUser(Usuario usuario) {
        userRepository.insert(usuario);
    }
}
