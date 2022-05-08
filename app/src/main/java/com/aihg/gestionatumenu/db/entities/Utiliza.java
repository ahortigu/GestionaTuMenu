package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.UTILIZA;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = UTILIZA, primaryKeys = {"id_receta", "id_ingrediente"}, foreignKeys = {
        @ForeignKey(entity = Receta.class,
                parentColumns = "id_receta",
                childColumns = "id_receta"),
        @ForeignKey(entity = Ingrediente.class,
                parentColumns = "id_ingrediente",
                childColumns = "id_ingrediente")
})
public class Utiliza {
    @NonNull
    @Embedded
    public Receta id_receta;
    @NonNull
    @Embedded
    public Ingrediente id_ingrediente;

    @NonNull
    @ColumnInfo(name = "cantidad_utiliza")
    public int cantidad;

    public Utiliza() {}

    public Receta getId_receta() {
        return id_receta;
    }

    public void setId_receta(Receta id_receta) {
        this.id_receta = id_receta;
    }

    public Ingrediente getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(Ingrediente id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Utiliza{" +
                "id_receta=" + id_receta +
                ", id_ingrediente=" + id_ingrediente +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utiliza)) return false;

        Utiliza utiliza = (Utiliza) o;

        if (cantidad != utiliza.cantidad) return false;
        if (!id_receta.equals(utiliza.id_receta)) return false;
        return id_ingrediente.equals(utiliza.id_ingrediente);
    }

    @Override
    public int hashCode() {
        int result = id_receta.hashCode();
        result = 31 * result + id_ingrediente.hashCode();
        result = 31 * result + cantidad;
        return result;
    }

    @Ignore
    public Utiliza(Receta id_receta, Ingrediente id_ingrediente, int cantidad) {
        this.id_receta = id_receta;
        this.id_ingrediente = id_ingrediente;
        this.cantidad = cantidad;
    }
}
