package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.MEDICIONES;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aihg.gestionatumenu.db.entities.Medicion;

import java.util.List;

@Dao
public interface MedicionDAO {
    @Insert
    void insert(Medicion medicion);

    @Query("SELECT * FROM " + MEDICIONES + " ORDER BY id_medicion ASC")
    LiveData<List<Medicion>> getAllMediciones();
}