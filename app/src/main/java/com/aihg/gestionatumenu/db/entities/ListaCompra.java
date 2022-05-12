package com.aihg.gestionatumenu.db.entities;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.LISTACOMPRA;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = LISTACOMPRA)
public class ListaCompra implements Serializable, IngredienteInterface {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_lista_compra",  index = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "cantidad_lista_compra",  index = true)
    public int cantidad;

    @Embedded
    public Ingrediente ingrediente;

    public ListaCompra() {}

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
        return "ListaCompra{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", ingrediente=" + ingrediente +
                '}';
    }

    @Ignore
    public ListaCompra(@NonNull int cantidad, Ingrediente ingrediente) {
        this.cantidad = cantidad;
        this.ingrediente = ingrediente;
    }

    @Ignore
    public ListaCompra(int id, int cantidad, Ingrediente ingrediente) {
        this.id = id;
        this.cantidad = cantidad;
        this.ingrediente = ingrediente;
    }
}
