package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.CATALOGA;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.PLANIFICADOR;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.RECETAS;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.SEMANAL;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.UTILIZA;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM " + RECETAS + " ORDER BY nombre_receta ASC")
    LiveData<List<Receta>> getAllRecetas();

    @Query("SELECT * FROM " + RECETAS + " WHERE nombre_receta LIKE :nombre")
    LiveData<Receta> getRecetaByNombre(String nombre);

    @Query(
        "SELECT DISTINCT * FROM " + RECETAS + " r WHERE " +
        "r.id_receta IN ( SELECT s.id_receta FROM " + SEMANAL + " s WHERE s.id_receta = r.id_receta ) OR " +
        "r.id_receta IN ( SELECT p.id_receta FROM " + PLANIFICADOR + " p WHERE p.id_receta = r.id_receta )"
    )
    LiveData<List<Receta>> getRecetasUtilizadasMenuPlanificador();

    @Query("DELETE FROM " + CATALOGA + " WHERE id_receta = :id_receta")
    void deleteCategoriasReceta(int id_receta);

    @Query("DELETE FROM " + UTILIZA + " WHERE id_receta = :id_receta")
    void deleteIngredientesReceta(int id_receta);
}
