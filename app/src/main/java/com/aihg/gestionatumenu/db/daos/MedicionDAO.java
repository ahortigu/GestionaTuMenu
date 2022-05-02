package com.aihg.gestionatumenu.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aihg.gestionatumenu.db.entities.Medicion;

import java.util.List;

@Dao
public interface MedicionDAO {
    @Insert
    void Insert(Medicion medicion);

    @Query("SELECT * FROM mediciones ORDER BY id ASC")
    LiveData<List<Medicion>> getAllMediciones();
}