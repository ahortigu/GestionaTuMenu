package com.aihg.gestionatumenu.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO;
import com.aihg.gestionatumenu.db.daos.CategoriaRecetaDAO;
import com.aihg.gestionatumenu.db.daos.DespensaDAO;
import com.aihg.gestionatumenu.db.daos.IngredienteDAO;
import com.aihg.gestionatumenu.db.daos.ListaCompraDAO;
import com.aihg.gestionatumenu.db.daos.RecetaDAO;
import com.aihg.gestionatumenu.db.database.GestionaTuMenuDatabase;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.db.entities.Receta;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestionaTuMenuRepository {

    //Intancia DB
    private GestionaTuMenuDatabase database;

    // DAO
    private CategoriaIngredienteDAO categoriaIngredienteDAO;
    private IngredienteDAO ingredienteDAO;
    private ListaCompraDAO listaCompraDAO;
    private DespensaDAO despensaDAO;
    private CategoriaRecetaDAO categoriaRecetaDAO;
    private RecetaDAO recetaDAO;

    // Listas
    private LiveData<List<CategoriaIngrediente>> categoriasIngrediente;
    private LiveData<List<Ingrediente>> ingredientes;
    private LiveData<List<ListaCompra>> listaCompra;
    private LiveData<List<Despensa>> despensa;
    private LiveData<List<CategoriaReceta>> categoriasReceta;
    private LiveData<List<Receta>> recetas;


    // Executors
    private ExecutorService executors = Executors.newSingleThreadExecutor();

    // Constructor
    public GestionaTuMenuRepository(Application application) {

        database = GestionaTuMenuDatabase.getDbInstance(application);

        // Interfaces
        categoriaIngredienteDAO = database.categoriaIngredienteDAO();
        ingredienteDAO = database.ingredienteDAO();
        listaCompraDAO = database.listaCompraDAO();
        despensaDAO = database.despensaDAO();
        categoriaRecetaDAO = database.categoriaRecetaDAO();
        recetaDAO = database.recetaDAO();

        // Lists
        categoriasIngrediente = categoriaIngredienteDAO.getAllCategoriasIngrediente();
        ingredientes = ingredienteDAO.getAllIngredientes();
        listaCompra = listaCompraDAO.getAllListaCompra();
        despensa = despensaDAO.getAllDespensa();
        categoriasReceta = categoriaRecetaDAO.getAllCategoriasReceta();
        recetas = recetaDAO.getAllRecetas();
    }

    public LiveData<List<CategoriaIngrediente>> getAllCategoriasIngrediente() {
        return categoriasIngrediente;
    }

    public LiveData<List<Ingrediente>> getAllIngredientes() {

        return ingredientes;
    }

    public  LiveData<List<ListaCompra>> getALLListaCompra(){

        return listaCompra;
    }

    public  LiveData<List<Despensa>> getALLDespensa(){

        return despensa;
    }

    public  LiveData<List<CategoriaReceta>> getALLCategoriasReceta(){

        return categoriasReceta;
    }

    public  LiveData<List<Receta>> getALLRecetas(){

        return recetas;
    }

    public void insert(Ingrediente ingrediente) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                ingredienteDAO.insert(ingrediente);
            }
        });
    }

    public void delete(Ingrediente ingrediente) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                ingredienteDAO.delete(ingrediente);
            }
        });
    }

    public void update(Ingrediente ingrediente) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                ingredienteDAO.update(ingrediente);
            }
        });
    }

    public void insert(ListaCompra listaCompra) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                listaCompraDAO.insert(listaCompra);
            }
        });
    }

    public void delete(ListaCompra listaCompra) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                listaCompraDAO.delete(listaCompra);
            }
        });
    }

    public void update(ListaCompra listaCompra) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                listaCompraDAO.update(listaCompra);
            }
        });
    }

    public void insert(Despensa despensa) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                despensaDAO.insert(despensa);
            }
        });
    }

    public void delete(Despensa despensa) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                despensaDAO.delete(despensa);
            }
        });
    }

    public void update(Despensa despensa) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                despensaDAO.update(despensa);
            }
        });
    }

    public void insert(Receta receta) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                recetaDAO.insert(receta);
            }
        });
    }

    public void delete(Receta receta) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                recetaDAO.delete(receta);
            }
        });
    }

    public void update(Receta receta) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                recetaDAO.update(receta);
            }
        });
    }
}
