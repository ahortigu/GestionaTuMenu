package com.aihg.gestionatumenu.db.daos;

import static com.aihg.gestionatumenu.db.util.DatabaseTables.MENU;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.aihg.gestionatumenu.db.entities.Menu;

import java.util.List;

@Dao
public interface MenuDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Menu menu);

    @Delete
    void delete(Menu menu);

    @Update
    void update(Menu menu);

    @Query("SELECT * FROM " + MENU)
    LiveData<List<Menu>> getAllMenus();
}
