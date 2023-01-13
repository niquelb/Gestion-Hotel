package com.example.gestionhotel.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuarios")
public class Usuario {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    String email;

    @ColumnInfo(name = "nombre")
    String nombre;

    @ColumnInfo(name = "apellidos")
    String apellidos;

    @NonNull
    @ColumnInfo(name = "password")
    String password;

    public Usuario(@NonNull String email, String nombre, String apellidos, @NonNull String password) {
        this.email = email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
