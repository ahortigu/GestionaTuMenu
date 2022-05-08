package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.UTILIZA;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Utiliza;

import java.util.List;

@Dao
public interface UtilizaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Utiliza utiliza);

    @Delete
    void delete(Utiliza utiliza);

    @Update
    void update(Utiliza utiliza);

    @Query("SELECT * FROM " + UTILIZA)
    LiveData<List<Utiliza>> getAllUtiliza();
}
