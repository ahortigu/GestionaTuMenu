package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.CATEGORIAS_INGREDIENTE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = CATEGORIAS_INGREDIENTE,
    indices = {@Index(
        value = {"id_categoria", "nombre_categoria"}
    )}
)
public class CategoriaIngrediente {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_categoria",  index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre_categoria",  index = true)
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
        if (!(o instanceof CategoriaIngrediente)) return false;

        CategoriaIngrediente that = (CategoriaIngrediente) o;

        if (id != that.id) return false;
        return nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CategoriaIngrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public CategoriaIngrediente() {}

    @Ignore
    public CategoriaIngrediente(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @Ignore
    public CategoriaIngrediente(@NonNull int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

