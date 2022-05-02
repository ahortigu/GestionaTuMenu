package com.aihg.gestionatumenu.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "mediciones", indices = {@Index(value = {"nombre"}, unique = true)})
public class Medicion {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String nombre;

    public Medicion(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }
}