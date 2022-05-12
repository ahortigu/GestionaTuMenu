package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.PLANIFICADOR;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Planificador;

import java.util.List;

@Dao
public interface PlanificadorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Planificador planificador);

    @Delete
    void delete(Planificador planificador);

    @Update
    void update(Planificador planificador);

    @Query("SELECT * FROM " + PLANIFICADOR)
    LiveData<List<Planificador>> getAllPlanificador();
}
