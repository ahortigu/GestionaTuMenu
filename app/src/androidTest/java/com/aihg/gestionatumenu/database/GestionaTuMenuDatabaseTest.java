package com.aihg.gestionatumenu.database;

import static com.aihg.gestionatumenu.database.util.LiveDataTestUtil.getOrAwaitValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO;
import com.aihg.gestionatumenu.db.daos.IngredienteDAO;
import com.aihg.gestionatumenu.db.database.GestionaTuMenuDatabase;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GestionaTuMenuDatabaseTest {
    private CategoriaIngredienteDAO categoriaIngredienteDAO;
    private IngredienteDAO ingredienteDAO;
    private GestionaTuMenuDatabase db;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, GestionaTuMenuDatabase.class)
                .allowMainThreadQueries()
                .build();

        categoriaIngredienteDAO = db.categoriaIngredienteDAO();
        ingredienteDAO = db.ingredienteDAO();
    }

    @After
    public void tearDown() throws Exception {
        if (db != null) {
            db.close();
        }
    }

    @Test
    public void test_InsertCategoriaIngredientes() throws InterruptedException {
        CategoriaIngrediente verdura = new CategoriaIngrediente();
        verdura.setNombre("verdura");
        List<CategoriaIngrediente> esperado = Arrays.asList(verdura);

        categoriaIngredienteDAO.insert(verdura);

        List<CategoriaIngrediente> liveActual = getOrAwaitValue(
                categoriaIngredienteDAO.getAllCategoriasIngrediente()
        );
        assertEquals(esperado, liveActual);
    }

    @Test
    public void test_InsertIngrediente() throws InterruptedException {
        categoriaIngredienteDAO.insert(new CategoriaIngrediente("Verduras"));
        List<CategoriaIngrediente> categorias = getOrAwaitValue(
                categoriaIngredienteDAO.getAllCategoriasIngrediente()
        );

        assertFalse(categorias.isEmpty());

        CategoriaIngrediente catVerduras = categorias.get(0);

        Ingrediente lechuga = new Ingrediente("lechuga", catVerduras.getId());
        ingredienteDAO.insert(lechuga);

        List<Ingrediente> ingredientes = getOrAwaitValue(
                ingredienteDAO.getAllIngredientes()
        );

        assertFalse(ingredientes.isEmpty());
        Ingrediente first = ingredientes.get(0);
        assertEquals(first.getIdCategoria(), catVerduras.getId());
        assertEquals(first.getNombre(), lechuga.getNombre());
    }
}