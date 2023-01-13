package com.example.gestionhotel.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.gestionhotel.data.AppDB;
import com.example.gestionhotel.data.daos.UsuarioDao;
import com.example.gestionhotel.data.entities.Usuario;

public class UsuarioRepository {

    private UsuarioDao usuarioDao;

    public UsuarioRepository(Context context){
        AppDB db= AppDB.getInstance(context);
        usuarioDao=db.usuarioDao();
    }

    public void insert (Usuario usuario){
        AppDB.dbExecutor.execute(
                ()->usuarioDao.insert(usuario)
        );
    }

    public void update (Usuario usuario) {
        AppDB.dbExecutor.execute(
                ()->usuarioDao.update(usuario)
        );
    }

    public LiveData<Usuario> getOne(String mEmail, String password){
        return usuarioDao.getOne(mEmail, password);
    }

}