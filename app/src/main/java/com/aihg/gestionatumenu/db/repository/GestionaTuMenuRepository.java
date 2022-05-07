package com.aihg.gestionatumenu.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO;
import com.aihg.gestionatumenu.db.daos.IngredienteDAO;
import com.aihg.gestionatumenu.db.daos.ListaCompraDAO;
import com.aihg.gestionatumenu.db.database.GestionaTuMenuDatabase;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.ListaCompra;

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

    // Listas
    private LiveData<List<CategoriaIngrediente>> categoriasIngrediente;
    private LiveData<List<Ingrediente>> ingredientes;
    private LiveData<List<ListaCompra>> listaCompra;

    // Executors
    private ExecutorService executors = Executors.newSingleThreadExecutor();

    // Constructor
    public GestionaTuMenuRepository(Application application) {

        database = GestionaTuMenuDatabase.getDbInstance(application);

        // Interfaces
        categoriaIngredienteDAO = database.categoriaIngredienteDAO();
        ingredienteDAO = database.ingredienteDAO();
        listaCompraDAO = database.listaCompraDAO();

        // Lists
        categoriasIngrediente = categoriaIngredienteDAO.getAllCategoriasIngrediente();
        ingredientes = ingredienteDAO.getAllIngredientes();
        listaCompra = listaCompraDAO.getAllListaCompra();
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
}
