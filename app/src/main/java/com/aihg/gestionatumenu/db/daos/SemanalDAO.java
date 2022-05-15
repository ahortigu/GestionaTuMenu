package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.SEMANAL;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Semanal;

import java.util.List;

@Dao
public interface SemanalDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Semanal semanal);

    @Update
    void update(Semanal semanal);

    @Query("SELECT * FROM " + SEMANAL)
    LiveData<List<Semanal>> getAllSemanal();
}
