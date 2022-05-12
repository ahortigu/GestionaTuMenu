package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.DIA;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aihg.gestionatumenu.db.entities.Dia;

import java.util.List;

@Dao
public interface DiaDAO {
    @Insert
    void insert(Dia dia);

    @Query("SELECT * FROM " + DIA + " ORDER BY id_dia ASC")
    LiveData<List<Dia>> getAllDias();
}
