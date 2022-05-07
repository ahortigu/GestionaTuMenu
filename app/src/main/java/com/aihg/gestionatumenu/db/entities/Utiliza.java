package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.UTILIZA;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = UTILIZA, primaryKeys = {"id_Receta", "id_Ingrediente"})
public class Utiliza {
    @Embedded
    @ColumnInfo(name = "id_Receta", index = true)
    public int idReceta;

    @Embedded
    @ColumnInfo(name = "id_Ingrediente", index = true)
    public int idIngrediente;

    @NonNull
    public int cantidad;

    public Utiliza() {
    }

    @Override
    public String toString() {
        return "Utiliza{" +
                "idReceta=" + idReceta +
                ", idIngrediente=" + idIngrediente +
                ", cantidad=" + cantidad +
                '}';
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
