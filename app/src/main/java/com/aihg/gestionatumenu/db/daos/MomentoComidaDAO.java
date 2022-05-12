package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.MOMENTO_COMIDA;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aihg.gestionatumenu.db.entities.MomentoComida;

import java.util.List;

@Dao
public interface MomentoComidaDAO {
    @Insert
    void insert(MomentoComida momentoComida);

    @Query("SELECT * FROM " + MOMENTO_COMIDA + " ORDER BY id_momento_comida ASC")
    LiveData<List<MomentoComida>> getAllMomentosComida();
}
