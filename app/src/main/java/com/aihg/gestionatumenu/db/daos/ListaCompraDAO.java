package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.LISTACOMPRA;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.ListaCompra;

import java.util.List;

@Dao
public interface ListaCompraDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListaCompra listaCompra);

    @Delete
    void delete(ListaCompra listaCompra);

    @Update
    void update(ListaCompra listaCompra);

    @Query("SELECT * FROM " + LISTACOMPRA + " ORDER BY id_lista_compra ASC")
    LiveData<List<ListaCompra>> getAllListaCompra();
}
