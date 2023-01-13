package com.example.gestionhotel.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habitaciones")
public class Habitacion {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="id")
    String id;

    @ColumnInfo(name ="descrip")
    String descrip;

    @ColumnInfo(name ="precio")
    double precio;

    @ColumnInfo(name ="imagen")
    int imagen;

    public Habitacion(@NonNull String id, String descrip, double precio, int imagen) {
        this.id = id;
        this.descrip = descrip;
        this.precio = precio;
        this.imagen = imagen;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
