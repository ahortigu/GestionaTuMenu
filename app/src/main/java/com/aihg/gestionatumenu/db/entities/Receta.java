package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.RECETAS;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = RECETAS)
public class Receta implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_receta",  index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre_receta",  index = true)
    public String nombre;

    @NonNull
    public String instrucciones;

    public Receta() {
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", instrucciones='" + instrucciones + '\'' +
                '}';
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

    @NonNull
    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(@NonNull String instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Ignore
    public Receta(int id, @NonNull String nombre, @NonNull String instrucciones) {
        this.id = id;
        this.nombre = nombre;
        this.instrucciones = instrucciones;
    }

    @Ignore
    public Receta(@NonNull String nombre, @NonNull String instrucciones) {
        this.nombre = nombre;
        this.instrucciones = instrucciones;
    }
}
