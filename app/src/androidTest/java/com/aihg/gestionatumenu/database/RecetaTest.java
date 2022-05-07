package com.aihg.gestionatumenu.database;

import static com.aihg.gestionatumenu.database.util.LiveDataTestUtil.getOrAwaitValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.aihg.gestionatumenu.db.daos.CategoriaRecetaDAO;
import com.aihg.gestionatumenu.db.daos.RecetaDAO;
import com.aihg.gestionatumenu.db.database.GestionaTuMenuDatabase;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Receta;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RecetaTest {
    private RecetaDAO recetaDAO;
    private CategoriaRecetaDAO categoriaRecetaDAO;
    private GestionaTuMenuDatabase db;


    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, GestionaTuMenuDatabase.class)
                .allowMainThreadQueries()
                .build();
        recetaDAO = db.recetaDAO();
        categoriaRecetaDAO = db.categoriaRecetaDAO();
    }

    @After
    public void tearDown() throws Exception {
        if (db != null) {
            db.close();
        }
    }

    @Test
    public void test_InsertReceta() throws InterruptedException {

        recetaDAO.insert(new Receta("Huevos fritos", "Freir huevos con cuidados"));
        List<Receta> recetas = getOrAwaitValue(
                recetaDAO.getAllRecetas()
        );
        assertFalse(recetas.isEmpty());
        Receta actual = recetas.get(0);
        assertEquals(actual.nombre, "Huevos fritos");
        assertEquals(actual.getInstrucciones(), "Freir huevos con cuidados");
    }

    @Test
    public void test_InsertCategoriaReceta() throws InterruptedException {
        categoriaRecetaDAO.insert(new CategoriaReceta("Pescados"));
        List<CategoriaReceta> categoriasReceta = getOrAwaitValue(
                categoriaRecetaDAO.getAllCategoriasReceta()
        );
        assertFalse(categoriasReceta.isEmpty());
        CategoriaReceta actual = categoriasReceta.get(0);
        assertEquals(actual.nombre, "Pescados");
    }
}
