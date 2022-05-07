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
    public int nombre;

    @NonNull
    public int instrucciones;

    public Receta() {
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", instrucciones=" + instrucciones +
                '}';
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(int instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Ignore
    public Receta(int nombre, int instrucciones) {
        this.nombre = nombre;
        this.instrucciones = instrucciones;
    }
}
