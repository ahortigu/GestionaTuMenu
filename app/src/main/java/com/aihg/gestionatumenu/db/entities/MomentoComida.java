package com.aihg.gestionatumenu.db.entities;


import static com.aihg.gestionatumenu.db.util.DatabaseTables.MOMENTO_COMIDA;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = MOMENTO_COMIDA)
public class MomentoComida {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_momento_comida",  index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre_momento_comida",  index = true)
    public String nombre;

    public MomentoComida() {
    }

    @Override
    public String toString() {
        return "MomentoComida{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MomentoComida)) return false;

        MomentoComida that = (MomentoComida) o;

        if (id != that.id) return false;
        return nombre.equals(that.nombre);
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
    public MomentoComida(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @Ignore
    public MomentoComida(int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
