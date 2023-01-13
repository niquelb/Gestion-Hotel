package com.example.gestionhotel.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gestionhotel.data.entities.Usuario;
import com.example.gestionhotel.data.repositories.UsuarioRepository;

public class UsuarioViewModel extends AndroidViewModel {
    private final UsuarioRepository uRepo;

    public UsuarioViewModel(@NonNull Application application) {
        super(new Application());
        this.uRepo = new UsuarioRepository(application);
    }

    public LiveData<Usuario> getUsuario(String miEmail, String miPassword) {
        return uRepo.getOne(miEmail, miPassword);
    }

    public void modifUsuario(Usuario usuario) {
        uRepo.update(usuario);
    }

    public void guardarUsuario(Usuario usuario) {
        uRepo.insert(usuario);
    }
}
