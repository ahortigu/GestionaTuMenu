package com.aihg.gestionatumenu.database;

import static com.aihg.gestionatumenu.database.util.LiveDataTestUtil.getOrAwaitValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO;
import com.aihg.gestionatumenu.db.daos.IngredienteDAO;
import com.aihg.gestionatumenu.db.daos.MedicionDAO;
import com.aihg.gestionatumenu.db.daos.RecetaDAO;
import com.aihg.gestionatumenu.db.daos.UtilizaDAO;
import com.aihg.gestionatumenu.db.GestionaTuMenuDatabase;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.Medicion;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UtilizaTest {
    private UtilizaDAO utilizaDAO;
    private CategoriaIngredienteDAO categoriaIngredienteDAO;
    private IngredienteDAO ingredienteDAO;
    private MedicionDAO medicionDAO;
    private RecetaDAO recetaDAO;
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
        recetaDAO = db.recetaDAO();
        utilizaDAO = db.utilizaDAO();
    }

    @After
    public void tearDown() throws Exception {
        if (db != null) {
            db.close();
        }
    }

    public void insertarIngredientes () throws InterruptedException {
        // Insert categoria
        categoriaIngredienteDAO.insert(new CategoriaIngrediente("Pasta"));
        List<CategoriaIngrediente> categorias = getOrAwaitValue(
                categoriaIngredienteDAO.getAllCategoriasIngrediente()
        );
        assertFalse(categorias.isEmpty());
        CategoriaIngrediente pasta = categorias.get(0);

        // Insert medicion
        medicionDAO.insert(new Medicion("Gr"));
        List<Medicion> mediciones = getOrAwaitValue(
                medicionDAO.getAllMediciones()
        );
        assertFalse(mediciones.isEmpty());
        Medicion unidad = mediciones.get(0);

        // Insert ingrediente
        ingredienteDAO.insert(new Ingrediente("Espaguettis",pasta, unidad));
        List<Ingrediente> ingredientes = getOrAwaitValue(
                ingredienteDAO.getAllIngredientes()
        );
        assertFalse(ingredientes.isEmpty());
    }

    public void insertarRecetas () throws InterruptedException {
        recetaDAO.insert(new Receta("Pasta Bolognese", "Cocer pasta etc etc"));
        List<Receta> recetas = getOrAwaitValue(
                recetaDAO.getAllRecetas()
        );
        assertFalse(recetas.isEmpty());
    }

    @Test
    public void test_InsertUtiliza() throws InterruptedException {
        insertarIngredientes();
        insertarRecetas();
        List<Ingrediente> ingredientes = getOrAwaitValue(
                ingredienteDAO.getAllIngredientes()
        );
        Ingrediente espaguetti = ingredientes.get(0);

        List<Receta> recetas = getOrAwaitValue(
                recetaDAO.getAllRecetas()
        );
        Receta pastaBolognese = recetas.get(0);

        utilizaDAO.insert(new Utiliza(pastaBolognese, espaguetti, 500));
        List<Utiliza> utiliza = getOrAwaitValue(
                utilizaDAO.getAllUtiliza()
        );
        assertFalse(utiliza.isEmpty());

        Utiliza actual = utiliza.get(0);
        assertEquals(actual.getId_ingrediente().getNombre(), espaguetti.getNombre());
        assertEquals(actual.getId_receta().getNombre(), pastaBolognese.getNombre());
        assertEquals(actual.getCantidad(), 500);
    }
}
