package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.DESPENSA;
import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.RECETAS;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Receta;

import java.util.List;

@Dao
public interface RecetaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Receta receta);

    @Delete
    void delete(Receta receta);

    @Update
    void update(Receta receta);

    @Query("SELECT * FROM " + RECETAS + " ORDER BY id_receta ASC")
    LiveData<List<Receta>> getAllRecetas();
}
