package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.MEDICIONES;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = MEDICIONES,
    indices = {@Index(
        value = {"id_medicion", "nombre_medicion"}
    )}
)
public class Medicion {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_medicion",  index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre_medicion",  index = true)
    public String nombre;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicion)) return false;

        Medicion medicion = (Medicion) o;

        if (id != medicion.id) return false;
        return nombre.equals(medicion.nombre);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Medicion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public Medicion() {}

    @Ignore
    public Medicion(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @Ignore
    public Medicion(@NonNull int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}