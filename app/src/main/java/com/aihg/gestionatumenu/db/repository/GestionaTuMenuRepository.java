package com.aihg.gestionatumenu.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.aihg.gestionatumenu.db.daos.CatalogaDAO;
import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO;
import com.aihg.gestionatumenu.db.daos.CategoriaRecetaDAO;
import com.aihg.gestionatumenu.db.daos.DespensaDAO;
import com.aihg.gestionatumenu.db.daos.DiaDAO;
import com.aihg.gestionatumenu.db.daos.IngredienteDAO;
import com.aihg.gestionatumenu.db.daos.ListaCompraDAO;
import com.aihg.gestionatumenu.db.daos.MedicionDAO;
import com.aihg.gestionatumenu.db.daos.SemanalDAO;
import com.aihg.gestionatumenu.db.daos.MomentoComidaDAO;
import com.aihg.gestionatumenu.db.daos.PlanificadorDAO;
import com.aihg.gestionatumenu.db.daos.RecetaDAO;
import com.aihg.gestionatumenu.db.daos.UtilizaDAO;
import com.aihg.gestionatumenu.db.GestionaTuMenuDatabase;
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.db.entities.Medicion;
import com.aihg.gestionatumenu.db.entities.Semanal;
import com.aihg.gestionatumenu.db.entities.MomentoComida;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestionaTuMenuRepository {

    //Intancia DB
    private GestionaTuMenuDatabase database;

    // DAO
    private CategoriaIngredienteDAO categoriaIngredienteDAO;
    private IngredienteDAO ingredienteDAO;
    private MedicionDAO medicionDAO;

    private ListaCompraDAO listaCompraDAO;

    private DespensaDAO despensaDAO;

    private CategoriaRecetaDAO categoriaRecetaDAO;
    private RecetaDAO recetaDAO;
    private UtilizaDAO utilizaDAO;
    private CatalogaDAO catalogaDAO;

    private DiaDAO diaDAO;
    private MomentoComidaDAO momentoComidaDAO;
    private SemanalDAO semanalDAO;
    private PlanificadorDAO planificadorDAO;


    // Listas
    private LiveData<List<CategoriaIngrediente>> categoriasIngrediente;
    private LiveData<List<Ingrediente>> ingredientes;
    private LiveData<List<Medicion>> mediciones;

    private LiveData<List<ListaCompra>> listaCompra;

    private LiveData<List<Despensa>> despensa;

    private LiveData<List<CategoriaReceta>> categoriasReceta;
    private LiveData<List<Receta>> recetas;
    private LiveData<List<Utiliza>> utiliza;
    private LiveData<List<Cataloga>> cataloga;

    private LiveData<List<Dia>> dias;
    private LiveData<List<MomentoComida>> momentosComida;
    private LiveData<List<Semanal>> semanal;
    private LiveData<List<Planificador>> planificadorList;


    // Executors
    private ExecutorService executors = Executors.newSingleThreadExecutor();

    // Constructor
    public GestionaTuMenuRepository(Application application) {

        database = GestionaTuMenuDatabase.getDbInstance(application);

        // Interfaces
        categoriaIngredienteDAO = database.categoriaIngredienteDAO();
        ingredienteDAO = database.ingredienteDAO();
        medicionDAO = database.medicionDAO();

        listaCompraDAO = database.listaCompraDAO();

        despensaDAO = database.despensaDAO();

        categoriaRecetaDAO = database.categoriaRecetaDAO();
        recetaDAO = database.recetaDAO();
        utilizaDAO = database.utilizaDAO();
        catalogaDAO = database.catalogaDAO();

        diaDAO = database.diaDAO();
        momentoComidaDAO = database.momentoComidaDAO();
        semanalDAO = database.semanalDAO();
        planificadorDAO = database.planificadorDAO();


        // Lists
        categoriasIngrediente = categoriaIngredienteDAO.getAllCategoriasIngrediente();
        ingredientes = ingredienteDAO.getAllIngredientes();
        mediciones = medicionDAO.getAllMediciones();

        listaCompra = listaCompraDAO.getAllListaCompra();

        despensa = despensaDAO.getAllDespensa();

        categoriasReceta = categoriaRecetaDAO.getAllCategoriasReceta();
        recetas = recetaDAO.getAllRecetas();
        utiliza = utilizaDAO.getAllUtiliza();
        cataloga = catalogaDAO.getAllCataloga();

        dias = diaDAO.getAllDias();
        momentosComida = momentoComidaDAO.getAllMomentosComida();
        semanal = semanalDAO.getAllSemanal();
        planificadorList = planificadorDAO.getAllPlanificador();
    }

    public LiveData<List<CategoriaIngrediente>> getAllCategoriasIngrediente() {
        return categoriasIngrediente;
    }

    public LiveData<List<Ingrediente>> getAllIngredientes() {
        return ingredientes;
    }

    public LiveData<List<Medicion>> getAllMediciones() {
        return mediciones;
    }

    public LiveData<List<ListaCompra>> getALLListaCompra() {
        return listaCompra;
    }

    public LiveData<List<Despensa>> getALLDespensa() {
        return despensa;
    }

    public LiveData<List<CategoriaReceta>> getALLCategoriasReceta() {
        return categoriasReceta;
    }

    public LiveData<List<Receta>> getALLRecetas() {
        return recetas;
    }

    public LiveData<List<Utiliza>> getAllUtiliza() {
        return utiliza;
    }

    public LiveData<List<Cataloga>> getALLCataloga() {
        return cataloga;
    }

    public LiveData<List<Dia>> getAllDias() {
        return dias;
    }

    public LiveData<List<MomentoComida>> getAllMomentosComida() {
        return momentosComida;
    }

    public LiveData<List<Semanal>> getAllSemanal() {
        return semanal;
    }

    public LiveData<List<Planificador>> getAllPlanificador() {
        return planificadorList;
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

    public void insert(Utiliza utiliza) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                utilizaDAO.insert(utiliza);
            }
        });
    }

    public void delete(Utiliza utiliza) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                utilizaDAO.delete(utiliza);
            }
        });
    }

    public void update(Utiliza utiliza) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                utilizaDAO.update(utiliza);
            }
        });
    }

    public void insert(Cataloga cataloga) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                catalogaDAO.insert(cataloga);
            }
        });
    }

    public void delete(Cataloga cataloga) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                catalogaDAO.delete(cataloga);
            }
        });
    }

    public void update(Cataloga cataloga) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                catalogaDAO.update(cataloga);
            }
        });
    }

    public void insert(Semanal semanal) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                semanalDAO.insert(semanal);
            }
        });
    }

    public void update(Semanal semanal) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                semanalDAO.update(semanal);
            }
        });
    }

    public void insert(Planificador planificador) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                planificadorDAO.insert(planificador);
            }
        });
    }

    public void update(Planificador planificador) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                planificadorDAO.update(planificador);
            }
        });
    }

    public LiveData<List<Utiliza>> getUtilizaByReceta(Receta receta) {
        return utilizaDAO.getUtilizaByReceta(receta.getId());
    }

    public LiveData<List<Cataloga>> getCatalogaByReceta(Receta receta) {
        return catalogaDAO.getCatalogaByReceta(receta.getId());
    }

    public LiveData<List<Ingrediente>> getIngredientesParaBuscarDespensa() {
        return ingredienteDAO.getIngredientesParaBuscarDespensa();
    }

    public LiveData<List<Ingrediente>> getIngredientesBuscarListaCompra() {
        return ingredienteDAO.getIngredientesBuscarListaCompra();
    }

    public LiveData<List<Ingrediente>> getIngredienteBuscarReceta(Receta receta) {
        return ingredienteDAO.getIngredienteBuscarReceta(receta.getId());
    }

    public LiveData<List<Ingrediente>> getIngredienteByName(Ingrediente ingrediente) {
        return ingredienteDAO.getIngredienteByName(ingrediente.getNombre());
    }

    public LiveData<List<Ingrediente>> getIngredientesPuedenBorrar() {
        return ingredienteDAO.getIngredientesPuedenBorrar();
    }

    public LiveData<Receta> getRecetaByNombre(String nombre) {
        return recetaDAO.getRecetaByNombre(nombre);
    }

    public LiveData<List<Receta>> getRecetasUtilizadasMenuPlanificador() {
        return recetaDAO.getRecetasUtilizadasMenuPlanificador();
    }
}
