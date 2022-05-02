package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.INGREDIENTES;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = INGREDIENTES)
public class Ingrediente {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_ingrediente",  index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre_ingrediente",  index = true)
    public String nombre;

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    public byte[] imagen;

    @Embedded
    public Medicion medicion;

    @Embedded
    public CategoriaIngrediente categoriaIngrediente;

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

    public Medicion getMedicion() {
        return medicion;
    }

    public void setMedicion(Medicion medicion) {
        this.medicion = medicion;
    }

    public CategoriaIngrediente getCategoriaIngrediente() {
        return categoriaIngrediente;
    }

    public void setCategoriaIngrediente(CategoriaIngrediente categoriaIngrediente) {
        this.categoriaIngrediente = categoriaIngrediente;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", medicion=" + medicion +
                ", categoriaIngrediente=" + categoriaIngrediente +
                '}';
    }

    public Ingrediente() {}

    @Ignore
    public Ingrediente(@NonNull String nombre, CategoriaIngrediente categoria, Medicion medicion) {
        this.nombre = nombre;
        this.categoriaIngrediente = categoria;
        this.medicion = medicion;
    }
}