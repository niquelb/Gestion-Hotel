package com.example.gestionhotel.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservas", foreignKeys = {
        @ForeignKey(
                entity = Usuario.class,
                parentColumns = "email",
                childColumns = "id_usuario"
        ),
        @ForeignKey(
               entity = Habitacion.class,
                parentColumns = "id",
                childColumns = "id_habitacion"
        )
})
public class Reserva {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "fecha_in")
    private String fecha_in;

    @ColumnInfo(name = "fecha_out")
    private String fecha_out;

    @NonNull
    @ColumnInfo(name = "numGuests")
    private int numGuests;

    @ColumnInfo(name = "id_usuario")
    private String id_usuario;

    @ColumnInfo(name = "id_habitacion")
    private String id_habitacion;

    public Reserva(@NonNull String fecha_in, String fecha_out, String id_usuario, String id_habitacion, @NonNull int numGuests) {
        this.fecha_in = fecha_in;
        this.fecha_out = fecha_out;
        this.id_usuario = id_usuario;
        this.id_habitacion = id_habitacion;
        this.numGuests=numGuests;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getFecha_in() {
        return fecha_in;
    }

    public void setFecha_in(@NonNull String fecha_in) {
        this.fecha_in = fecha_in;
    }

    public String getFecha_out() {
        return fecha_out;
    }

    public void setFecha_out(String fecha_out) {
        this.fecha_out = fecha_out;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(String id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }
}
