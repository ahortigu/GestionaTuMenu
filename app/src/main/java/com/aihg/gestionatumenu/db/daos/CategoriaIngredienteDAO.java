package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.CATEGORIAS_INGREDIENTE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;

import java.util.List;

@Dao
public interface CategoriaIngredienteDAO {
    @Insert
    void insert(CategoriaIngrediente categoriaIngrediente);

    @Query("SELECT * FROM " + CATEGORIAS_INGREDIENTE + " ORDER BY id ASC")
    LiveData<List<CategoriaIngrediente>> getAllCategoriasIngrediente();
}