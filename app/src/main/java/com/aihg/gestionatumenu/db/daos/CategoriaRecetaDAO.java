package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.CATEGORIAS_RECETA;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aihg.gestionatumenu.db.entities.CategoriaReceta;

import java.util.List;

@Dao
public interface CategoriaRecetaDAO {
    @Insert
    void insert(CategoriaReceta categoriaReceta);

    @Query("SELECT * FROM " + CATEGORIAS_RECETA + " ORDER BY nombre_categoria_receta ASC")
    LiveData<List<CategoriaReceta>> getAllCategoriasReceta();
}
