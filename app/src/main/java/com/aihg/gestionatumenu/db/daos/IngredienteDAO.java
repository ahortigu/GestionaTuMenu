package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.INGREDIENTES;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Ingrediente;

import java.util.List;

@Dao
public interface IngredienteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Ingrediente ingrediente);

    @Delete
    void delete(Ingrediente ingrediente);

    @Update
    void update(Ingrediente ingrediente);

    @Query("SELECT * FROM " + INGREDIENTES + " ORDER BY id_ingrediente ASC")
    LiveData<List<Ingrediente>> getAllIngredientes();
}