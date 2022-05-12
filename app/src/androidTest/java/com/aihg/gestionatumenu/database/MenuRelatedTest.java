package com.aihg.gestionatumenu.database;

import static com.aihg.gestionatumenu.database.util.LiveDataTestUtil.getOrAwaitValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.aihg.gestionatumenu.db.GestionaTuMenuDatabase;
import com.aihg.gestionatumenu.db.daos.DiaDAO;
import com.aihg.gestionatumenu.db.daos.MenuDAO;
import com.aihg.gestionatumenu.db.daos.MomentoComidaDAO;
import com.aihg.gestionatumenu.db.daos.PlanificadorDAO;
import com.aihg.gestionatumenu.db.daos.RecetaDAO;
import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.Menu;
import com.aihg.gestionatumenu.db.entities.MomentoComida;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.db.entities.Receta;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MenuRelatedTest {

    private MenuDAO menuDAO;
    private PlanificadorDAO planificadorDAO;
    private DiaDAO diaDAO;
    private MomentoComidaDAO momentoComidaDAO;
    private RecetaDAO recetaDAO;

    private GestionaTuMenuDatabase db;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, GestionaTuMenuDatabase.class)
                .allowMainThreadQueries()
                .build();
        diaDAO = db.diaDAO();
        momentoComidaDAO = db.momentoComidaDAO();
        menuDAO = db.menuDAO();
        planificadorDAO = db.planificadorDAO();
        recetaDAO = db.recetaDAO();
    }

    @After
    public void tearDown() throws Exception {
        if (db != null) {
            db.close();
        }
    }

    public void insertarDias() throws InterruptedException {
        diaDAO.insert(new Dia(1, "LUNES"));
        List<Dia> dias = getOrAwaitValue(
                diaDAO.getAllDias()
        );
        assertFalse(dias.isEmpty());
    }

    public void insertarMomentosComida() throws InterruptedException {
        momentoComidaDAO.insert(new MomentoComida(1, "DESAYUNO"));
        List<MomentoComida> momentos = getOrAwaitValue(
                momentoComidaDAO.getAllMomentosComida()
        );
        assertFalse(momentos.isEmpty());
    }


    public void insertarRecetas() throws InterruptedException {
        recetaDAO.insert(new Receta("LECHE CON GALLETAS", "Calentar leche etc etc"));
        List<Receta> recetas = getOrAwaitValue(
                recetaDAO.getAllRecetas()
        );
        assertFalse(recetas.isEmpty());
    }


    public void insertDefaultData() throws InterruptedException {
        insertarDias();
        insertarMomentosComida();
        insertarRecetas();
    }

    @Test
    public void test_InsertDia() throws InterruptedException {
        diaDAO.insert(new Dia(1, "LUNES"));
        List<Dia> dias = getOrAwaitValue(
                diaDAO.getAllDias()
        );
        assertFalse(dias.isEmpty());
        Dia actual = dias.get(0);
        assertEquals(actual.nombre, "LUNES");
    }

    @Test
    public void test_InsertMomentoComida() throws InterruptedException {
        momentoComidaDAO.insert(new MomentoComida(1, "DESAYUNO"));
        List<MomentoComida> momentos = getOrAwaitValue(
                momentoComidaDAO.getAllMomentosComida()
        );
        assertFalse(momentos.isEmpty());
        MomentoComida actual = momentos.get(0);
        assertEquals(actual.nombre, "DESAYUNO");
    }


    @Test
    public void test_InsertPlanificador() throws InterruptedException {
        insertDefaultData();

        List<Dia> dias = getOrAwaitValue(
                diaDAO.getAllDias()
        );
        Dia lunes = dias.get(0);

        List<MomentoComida> momentos = getOrAwaitValue(
                momentoComidaDAO.getAllMomentosComida()
        );
        MomentoComida desayuno = momentos.get(0);


        List<Receta> recetas = getOrAwaitValue(
                recetaDAO.getAllRecetas()
        );
        Receta lecheGalletas = recetas.get(0);

        planificadorDAO.insert(new Planificador(1, lecheGalletas, desayuno, lunes));
        List<Planificador> planificador = getOrAwaitValue(
                planificadorDAO.getAllPlanificador()
        );
        assertFalse(planificador.isEmpty());
        Planificador actual = planificador.get(0);
        assertEquals(actual.id_dia.getNombre(), "LUNES");
        assertEquals(actual.id_momento_comida.getNombre(), "DESAYUNO");
        assertEquals(actual.id_receta.getNombre(), "LECHE CON GALLETAS");
    }

    @Test
    public void test_InsertMenu() throws InterruptedException {
        insertDefaultData();

        List<Dia> dias = getOrAwaitValue(
                diaDAO.getAllDias()
        );
        Dia lunes = dias.get(0);

        List<MomentoComida> momentos = getOrAwaitValue(
                momentoComidaDAO.getAllMomentosComida()
        );
        MomentoComida desayuno = momentos.get(0);


        List<Receta> recetas = getOrAwaitValue(
                recetaDAO.getAllRecetas()
        );
        Receta lecheGalletas = recetas.get(0);

        menuDAO.insert(new Menu(1, lecheGalletas, desayuno, lunes));
        List<Menu> menus = getOrAwaitValue(
                menuDAO.getAllMenus()
        );
        assertFalse(menus.isEmpty());
        Menu actual = menus.get(0);
        assertEquals(actual.id_dia.getNombre(), "LUNES");
        assertEquals(actual.id_momento_comida.getNombre(), "DESAYUNO");
        assertEquals(actual.id_receta.getNombre(), "LECHE CON GALLETAS");
    }
}
