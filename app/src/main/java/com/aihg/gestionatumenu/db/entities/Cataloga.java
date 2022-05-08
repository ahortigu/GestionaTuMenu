package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.CATALOGA;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

@Entity(tableName = CATALOGA, primaryKeys = {"id_receta", "id_categoria_receta"}, foreignKeys = {
        @ForeignKey(entity = Receta.class,
                parentColumns = "id_receta",
                childColumns = "id_receta"),
        @ForeignKey(entity = CategoriaReceta.class,
                parentColumns = "id_categoria_receta",
                childColumns = "id_categoria_receta")
})
public class Cataloga {
    @NonNull
    @Embedded
    public Receta id_receta;

    @NonNull
    @Embedded
    public CategoriaReceta id_categoria_receta;

    public Cataloga() {
    }

    @Override
    public String toString() {
        return "Cataloga{" +
                "id_receta=" + id_receta +
                ", id_categoria_receta=" + id_categoria_receta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cataloga)) return false;

        Cataloga cataloga = (Cataloga) o;

        if (!id_receta.equals(cataloga.id_receta)) return false;
        return id_categoria_receta.equals(cataloga.id_categoria_receta);
    }

    @Override
    public int hashCode() {
        int result = id_receta.hashCode();
        result = 31 * result + id_categoria_receta.hashCode();
        return result;
    }

    @NonNull
    public Receta getId_receta() {
        return id_receta;
    }

    public void setId_receta(@NonNull Receta id_receta) {
        this.id_receta = id_receta;
    }

    @NonNull
    public CategoriaReceta getId_categoria_receta() {
        return id_categoria_receta;
    }

    public void setId_categoria_receta(@NonNull CategoriaReceta id_categoria_receta) {
        this.id_categoria_receta = id_categoria_receta;
    }

    @Ignore
    public Cataloga(@NonNull Receta id_receta, @NonNull CategoriaReceta id_categoria_receta) {
        this.id_receta = id_receta;
        this.id_categoria_receta = id_categoria_receta;
    }
}
