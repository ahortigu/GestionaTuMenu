package com.aihg.gestionatumenu.db.entities;

import static androidx.room.ForeignKey.CASCADE;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.DESPENSA;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity(
    tableName = DESPENSA,
    foreignKeys = @ForeignKey(
        entity = Ingrediente.class,
        parentColumns = "id_ingrediente",
        childColumns = "id_ingrediente"
    ),
    indices = {@Index(
        value = {"id_despensa", "id_ingrediente"}
    )}
)
public class Despensa implements Serializable, IngredienteInterface {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_despensa", index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "cantidad_despensa")
    public int cantidad;

    @Embedded
    public Ingrediente ingrediente;

    public Despensa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }


    @Override
    public String toString() {
        return "Despensa{" +
            "id=" + id +
            ", cantidad=" + cantidad +
            ", ingrediente=" + ingrediente +
            '}';
    }

    @Ignore
    public Despensa(@NonNull int cantidad, Ingrediente ingrediente) {
        this.cantidad = cantidad;
        this.ingrediente = ingrediente;
    }

    @Ignore
    public Despensa(int id, int cantidad, Ingrediente ingrediente) {
        this.id = id;
        this.cantidad = cantidad;
        this.ingrediente = ingrediente;
    }
}
