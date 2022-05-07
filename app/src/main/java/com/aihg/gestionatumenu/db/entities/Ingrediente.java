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

import java.io.Serializable;

@Entity(tableName = INGREDIENTES)
public class Ingrediente implements Serializable {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingrediente)) return false;

        Ingrediente that = (Ingrediente) o;

        if (id != that.id) return false;
        if (!nombre.equals(that.nombre)) return false;
        if (medicion != null ? !medicion.equals(that.medicion) : that.medicion != null)
            return false;
        return categoriaIngrediente != null ? categoriaIngrediente.equals(that.categoriaIngrediente) : that.categoriaIngrediente == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + (medicion != null ? medicion.hashCode() : 0);
        result = 31 * result + (categoriaIngrediente != null ? categoriaIngrediente.hashCode() : 0);
        return result;
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