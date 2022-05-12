package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.MENU;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = MENU,
        foreignKeys = {
                @ForeignKey(
                        entity = Receta.class,
                        parentColumns = "id_receta",
                        childColumns = "id_receta"
                ),
                @ForeignKey(
                        entity = MomentoComida.class,
                        parentColumns = "id_momento_comida",
                        childColumns = "id_momento_comida"
                ),
                @ForeignKey(
                        entity = Dia.class,
                        parentColumns = "id_dia",
                        childColumns = "id_dia"
                )
        },
        indices = {
                @Index(
                        value = {"id_momento_comida", "id_dia"},
                        unique = true
                )
        }
)
public class Menu {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_menu", index = true)
    public int id;

    @NonNull
    @Embedded
    public Receta id_receta;

    @NonNull
    @Embedded
    public MomentoComida id_momento_comida;

    @NonNull
    @Embedded
    public Dia id_dia;

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", id_receta=" + id_receta +
                ", id_momento_comida=" + id_momento_comida +
                ", id_dia=" + id_dia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (!id_receta.equals(menu.id_receta)) return false;
        if (!id_momento_comida.equals(menu.id_momento_comida)) return false;
        return id_dia.equals(menu.id_dia);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + id_receta.hashCode();
        result = 31 * result + id_momento_comida.hashCode();
        result = 31 * result + id_dia.hashCode();
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Receta getId_receta() {
        return id_receta;
    }

    public void setId_receta(@NonNull Receta id_receta) {
        this.id_receta = id_receta;
    }

    @NonNull
    public MomentoComida getId_momento_comida() {
        return id_momento_comida;
    }

    public void setId_momento_comida(@NonNull MomentoComida id_momento_comida) {
        this.id_momento_comida = id_momento_comida;
    }

    @NonNull
    public Dia getId_dia() {
        return id_dia;
    }

    public void setId_dia(@NonNull Dia id_dia) {
        this.id_dia = id_dia;
    }

    @Ignore
    public Menu(@NonNull Receta id_receta, @NonNull MomentoComida id_momento_comida, @NonNull Dia id_dia) {
        this.id_receta = id_receta;
        this.id_momento_comida = id_momento_comida;
        this.id_dia = id_dia;
    }

    @Ignore
    public Menu(int id, @NonNull Receta id_receta, @NonNull MomentoComida id_momento_comida, @NonNull Dia id_dia) {
        this.id = id;
        this.id_receta = id_receta;
        this.id_momento_comida = id_momento_comida;
        this.id_dia = id_dia;
    }
}

