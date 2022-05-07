package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.CATEGORIAS_RECETA;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = CATEGORIAS_RECETA)
public class CategoriaReceta {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_categoria_receta",  index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre_categoria_receta",  index = true)
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
        if (!(o instanceof CategoriaReceta)) return false;

        CategoriaReceta that = (CategoriaReceta) o;

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
        return "CategoriaReceta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public CategoriaReceta() {}

    @Ignore
    public CategoriaReceta(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @Ignore
    public CategoriaReceta(@NonNull int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


}
