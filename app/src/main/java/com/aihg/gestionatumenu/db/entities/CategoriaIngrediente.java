package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.CATEGORIAS_INGREDIENTE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = CATEGORIAS_INGREDIENTE, indices = {@Index(value = {"nombre"}, unique = true)})
public class CategoriaIngrediente {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
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
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaIngrediente that = (CategoriaIngrediente) o;
        return nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    public CategoriaIngrediente() {}

    @Ignore
    public CategoriaIngrediente(@NonNull String nombre) {
        this.nombre = nombre;
    }
}

