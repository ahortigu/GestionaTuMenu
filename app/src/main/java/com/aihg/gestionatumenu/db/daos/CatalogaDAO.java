package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.database.util.DatabaseTables.CATALOGA;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Cataloga;

import java.util.List;

@Dao
public interface CatalogaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Cataloga cataloga);

    @Delete
    void delete(Cataloga cataloga);

    @Update
    void update(Cataloga cataloga);

    @Query("SELECT * FROM " + CATALOGA)
    LiveData<List<Cataloga>> getAllCataloga();

}