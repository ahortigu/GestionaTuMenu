package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.RECETAS;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Set;

@Entity(tableName = RECETAS)
public class Receta {
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
