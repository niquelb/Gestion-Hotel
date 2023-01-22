package com.example.gestionhotel.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gestionhotel.data.entities.Usuario;

@Dao
public interface UsuarioDao {
    @Insert
    void insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Query("SELECT * FROM usuarios WHERE email = :miEmail AND password=:miPass LIMIT 1")
    LiveData<Usuario> getOne(String miEmail, String miPass);

    @Query("SELECT * FROM usuarios WHERE email = :miEmail LIMIT 1")
    LiveData<Usuario> getOne(String miEmail);
}