package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.DIA;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = DIA)
public class Dia {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_dia",  index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre_dia",  index = true)
    public String nombre;

    public Dia() {}

    @Override
    public String toString() {
        return "Dia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dia)) return false;

        Dia dia = (Dia) o;

        if (id != dia.id) return false;
        return nombre.equals(dia.nombre);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        return result;
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

    @Ignore
    public Dia(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @Ignore
    public Dia(int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}


