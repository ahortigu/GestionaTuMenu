package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.DESPENSA;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.INGREDIENTES;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.LISTACOMPRA;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.UTILIZA;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Receta;

import java.util.List;

@Dao
public interface IngredienteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Ingrediente ingrediente);

    @Delete
    void delete(Ingrediente ingrediente);

    @Update
    void update(Ingrediente ingrediente);

    @Query("SELECT * FROM " + INGREDIENTES + " ORDER BY nombre_ingrediente ASC")
    LiveData<List<Ingrediente>> getAllIngredientes();

    @Query("SELECT * FROM " + INGREDIENTES + " WHERE nombre_ingrediente LIKE :ingrediente")
    LiveData<List<Ingrediente>> getIngredienteByName(String ingrediente);

    @Query(
        "SELECT * FROM " + INGREDIENTES + " WHERE id_ingrediente NOT IN (" +
        "    SELECT id_ingrediente FROM " + DESPENSA + " " +
        ") order by nombre_ingrediente ASC"
    )
    LiveData<List<Ingrediente>> getIngredientesParaBuscarDespensa();

    @Query(
        "SELECT * FROM " + INGREDIENTES + " WHERE id_ingrediente NOT IN (" +
        "    SELECT id_ingrediente FROM " + LISTACOMPRA + " " +
        ") order by nombre_ingrediente ASC"
    )
    LiveData<List<Ingrediente>> getIngredientesBuscarListaCompra();

    @Query(
        "SELECT * FROM " + INGREDIENTES + " WHERE id_ingrediente NOT IN (" +
        "    SELECT id_ingrediente FROM " + UTILIZA + " WHERE id_receta = :receta " +
        ") order by nombre_ingrediente ASC"
    )
    LiveData<List<Ingrediente>> getIngredienteBuscarReceta(int receta);

    @Query(
        "SELECT * FROM " + INGREDIENTES + " i WHERE " +
        "i.id_ingrediente NOT IN ( SELECT lc.id_ingrediente FROM " + LISTACOMPRA + " lc WHERE lc.id_ingrediente = i.id_ingrediente ) AND  " +
        "i.id_ingrediente NOT IN ( SELECT d.id_ingrediente FROM " + DESPENSA + " d WHERE d.id_ingrediente = i.id_ingrediente ) AND " +
        "i.id_ingrediente NOT IN ( SELECT u.id_ingrediente FROM " + UTILIZA + " u WHERE u.id_ingrediente = i.id_ingrediente )"
    )
    LiveData<List<Ingrediente>> getIngredientesPuedenBorrar();
}