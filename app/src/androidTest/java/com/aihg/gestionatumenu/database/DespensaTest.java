package com.aihg.gestionatumenu.database;

import static com.aihg.gestionatumenu.database.util.LiveDataTestUtil.getOrAwaitValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO;
import com.aihg.gestionatumenu.db.daos.DespensaDAO;
import com.aihg.gestionatumenu.db.daos.IngredienteDAO;
import com.aihg.gestionatumenu.db.daos.MedicionDAO;
import com.aihg.gestionatumenu.db.GestionaTuMenuDatabase;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DespensaTest {
    private DespensaDAO despensaDAO;
    private CategoriaIngredienteDAO categoriaIngredienteDAO;
    private IngredienteDAO ingredienteDAO;
    private MedicionDAO medicionDAO;
    private GestionaTuMenuDatabase db;


    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, GestionaTuMenuDatabase.class)
                .allowMainThreadQueries()
                .build();

        categoriaIngredienteDAO = db.categoriaIngredienteDAO();
        ingredienteDAO = db.ingredienteDAO();
        medicionDAO = db.medicionDAO();
        despensaDAO = db.despensaDAO();
    }

    @After
    public void tearDown() throws Exception {
        if (db != null) {
            db.close();
        }
    }

    public void insertarIngredientes () throws InterruptedException {
        // Insert categoria
        categoriaIngredienteDAO.insert(new CategoriaIngrediente("Carne"));
        List<CategoriaIngrediente> categorias = getOrAwaitValue(
                categoriaIngredienteDAO.getAllCategoriasIngrediente()
        );
        assertFalse(categorias.isEmpty());
        CategoriaIngrediente carne = categorias.get(0);

        // Insert medicion
        medicionDAO.insert(new Medicion("Unidad"));
        List<Medicion> mediciones = getOrAwaitValue(
                medicionDAO.getAllMediciones()
        );
        assertFalse(mediciones.isEmpty());
        Medicion unidad = mediciones.get(0);

        // Insert ingrediente
        ingredienteDAO.insert(new Ingrediente("Cordero",carne, unidad));
        List<Ingrediente> ingredientes = getOrAwaitValue(
                ingredienteDAO.getAllIngredientes()
        );
        assertFalse(ingredientes.isEmpty());
    }

    @Test
    public void test_InsertDespensa() throws InterruptedException {
        insertarIngredientes();
        List<Ingrediente> ingredientes = getOrAwaitValue(
                ingredienteDAO.getAllIngredientes()
        );
        Ingrediente cordero = ingredientes.get(0);

        despensaDAO.insert(new Despensa(2, cordero));
        List<Despensa> despensa = getOrAwaitValue(
                despensaDAO.getAllDespensa()
        );
        assertFalse(despensa.isEmpty());
        Despensa actual = despensa.get(0);
        assertEquals(actual.getIngrediente(), cordero);
        assertEquals(actual.getCantidad(), 2);
    }
}

