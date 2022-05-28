package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.DESPENSA;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.INGREDIENTES;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;

import java.util.List;

@Dao
public interface DespensaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Despensa despensa);

    @Delete
    void delete(Despensa despensa);

    @Update
    void update(Despensa despensa);

    @Query("SELECT * FROM " + DESPENSA + " ORDER BY nombre_ingrediente ASC")
    LiveData<List<Despensa>> getAllDespensa();

    @Query(
        "SELECT i.* FROM " + INGREDIENTES + " i, " + DESPENSA + " p " +
        "WHERE i.id_ingrediente = p.id_ingrediente AND i.id_categoria != p.id_categoria"
    )
    LiveData<List<Ingrediente>> getDespensasToUpdate();
}
