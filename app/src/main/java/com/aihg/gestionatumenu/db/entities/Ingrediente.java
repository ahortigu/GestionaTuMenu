package com.aihg.gestionatumenu.db.entities;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.INGREDIENTES;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = INGREDIENTES,
        indices = {@Index(value = {"nombre"}, unique = true)},
        foreignKeys = {
//                @ForeignKey(
//                        entity = Medicion.class,
//                        parentColumns = "id",
//                        childColumns = "id_medicion",
//                        onDelete = ForeignKey.CASCADE
//                ),
                @ForeignKey(
                        entity = CategoriaIngrediente.class,
                        parentColumns = "id",
                        childColumns = "id_categoria",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Ingrediente {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String nombre;

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    public byte[] imagen;

//    @ColumnInfo(name = "id_medicion", index = true)
//    @NonNull
//    public int idMedicion;

    @ColumnInfo(name = "id_categoria",  index = true)
    @NonNull
    public int idCategoria;

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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Ingrediente() {}

    @Ignore
    public Ingrediente(@NonNull String nombre, @NonNull int idCategoria) {
        this.nombre = nombre;
        this.idCategoria = idCategoria;
    }
}