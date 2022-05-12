package com.aihg.gestionatumenu.db;

import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.getDefaultCategoriasIngrediente;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.getDefaultIngredientes;
import static com.aihg.gestionatumenu.db.util.generator.IngredientesDataGenerator.getDefaultMediciones;
import static com.aihg.gestionatumenu.db.util.DatabaseTables.DATABASE_NAME;
import static com.aihg.gestionatumenu.db.util.generator.RecetasDataGenerator.getAsignacionIngredientesReceta;
import static com.aihg.gestionatumenu.db.util.generator.RecetasDataGenerator.getCatalogacionRecetas;
import static com.aihg.gestionatumenu.db.util.generator.RecetasDataGenerator.getDefaultCategoriasRecetas;
import static com.aihg.gestionatumenu.db.util.generator.RecetasDataGenerator.getDefaultRecetas;
import static com.aihg.gestionatumenu.db.util.generator.ListaCompraDataGenerator.getDefaultListaCompra;
import static com.aihg.gestionatumenu.db.util.generator.DespensaDataGenerator.getDefaultDespensa;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.aihg.gestionatumenu.db.daos.CatalogaDAO;
import com.aihg.gestionatumenu.db.daos.CategoriaIngredienteDAO;
import com.aihg.gestionatumenu.db.daos.CategoriaRecetaDAO;
import com.aihg.gestionatumenu.db.daos.DespensaDAO;
import com.aihg.gestionatumenu.db.daos.DiaDAO;
import com.aihg.gestionatumenu.db.daos.IngredienteDAO;
import com.aihg.gestionatumenu.db.daos.ListaCompraDAO;
import com.aihg.gestionatumenu.db.daos.MedicionDAO;
import com.aihg.gestionatumenu.db.daos.MenuDAO;
import com.aihg.gestionatumenu.db.daos.MomentoComidaDAO;
import com.aihg.gestionatumenu.db.daos.PlanificadorDAO;
import com.aihg.gestionatumenu.db.daos.RecetaDAO;
import com.aihg.gestionatumenu.db.daos.UtilizaDAO;
import com.aihg.gestionatumenu.db.entities.Cataloga;
import com.aihg.gestionatumenu.db.entities.CategoriaIngrediente;
import com.aihg.gestionatumenu.db.entities.CategoriaReceta;
import com.aihg.gestionatumenu.db.entities.Despensa;
import com.aihg.gestionatumenu.db.entities.Dia;
import com.aihg.gestionatumenu.db.entities.Ingrediente;
import com.aihg.gestionatumenu.db.entities.ListaCompra;
import com.aihg.gestionatumenu.db.entities.Medicion;
import com.aihg.gestionatumenu.db.entities.Menu;
import com.aihg.gestionatumenu.db.entities.MomentoComida;
import com.aihg.gestionatumenu.db.entities.Planificador;
import com.aihg.gestionatumenu.db.entities.Receta;
import com.aihg.gestionatumenu.db.entities.Utiliza;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {
                Ingrediente.class,
                CategoriaIngrediente.class,
                Medicion.class,
                ListaCompra.class,
                Despensa.class,
                CategoriaReceta.class,
                Receta.class,
                Utiliza.class,
                Cataloga.class,
                Dia.class,
                MomentoComida.class,
                Menu.class,
                Planificador.class
        },
        version = 1
)
public abstract class GestionaTuMenuDatabase extends RoomDatabase {

    // Instancia
    private static volatile GestionaTuMenuDatabase INSTANCE;

    // DAO
    public abstract CategoriaIngredienteDAO categoriaIngredienteDAO();

    public abstract IngredienteDAO ingredienteDAO();

    public abstract MedicionDAO medicionDAO();

    public abstract ListaCompraDAO listaCompraDAO();

    public abstract DespensaDAO despensaDAO();

    public abstract CategoriaRecetaDAO categoriaRecetaDAO();

    public abstract RecetaDAO recetaDAO();

    public abstract UtilizaDAO utilizaDAO();

    public abstract CatalogaDAO catalogaDAO();

    public abstract DiaDAO diaDAO();

    public abstract MomentoComidaDAO momentoComidaDAO();

    public abstract MenuDAO menuDAO();

    public abstract PlanificadorDAO planificadorDAO();


    private static Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // DAO
            CategoriaIngredienteDAO categoriaIngredienteDAO = INSTANCE.categoriaIngredienteDAO();
            IngredienteDAO ingredienteDAO = INSTANCE.ingredienteDAO();
            MedicionDAO medicionDAO = INSTANCE.medicionDAO();
            ListaCompraDAO listaCompraDAO = INSTANCE.listaCompraDAO();
            DespensaDAO despensaDAO = INSTANCE.despensaDAO();

            CategoriaRecetaDAO categoriaRecetaDAO = INSTANCE.categoriaRecetaDAO();
            RecetaDAO recetaDAO = INSTANCE.recetaDAO();
            UtilizaDAO utilizaDAO = INSTANCE.utilizaDAO();
            CatalogaDAO catalogaDAO = INSTANCE.catalogaDAO();

            DiaDAO diaDAO = INSTANCE.diaDAO();
            MomentoComidaDAO momentoComidaDAO = INSTANCE.momentoComidaDAO();
            MenuDAO menuDAO = INSTANCE.menuDAO();
            PlanificadorDAO planificadorDAO = INSTANCE.planificadorDAO();

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    crearIngredientes();
                    crearRecetas();
                    crearDespensa();
                    crearListaCompra();
                }

                private void crearIngredientes() {
                    Log.d("DEFAULT-DB", "Añadiendo valores por defecto de ingredientes.");
                    getDefaultCategoriasIngrediente().stream().forEach(
                            categoriaIngredienteDAO::insert
                    );

                    getDefaultMediciones().stream().forEach(
                            medicionDAO::insert
                    );

                    getDefaultIngredientes().stream().forEach(
                            ingredienteDAO::insert
                    );
                }

                private void crearDespensa() {
                    getDefaultDespensa().stream().forEach(
                            despensaDAO::insert
                    );
                }

                private void crearListaCompra() {
                    getDefaultListaCompra().stream().forEach(
                            listaCompraDAO::insert
                    );
                }

                private void crearRecetas() {
                    Log.d("DEFAULT-DB", "Añadiendo valores por defecto de recetas.");
                    getDefaultCategoriasRecetas().stream().forEach(
                            categoriaRecetaDAO::insert
                    );
                    getDefaultRecetas().stream().forEach(
                            recetaDAO::insert
                    );
                    getCatalogacionRecetas().stream().forEach(
                            catalogaDAO::insert
                    );
                    getAsignacionIngredientesReceta().stream().forEach(
                            utilizaDAO::insert
                    );
                }
            });
        }
    };


    public static GestionaTuMenuDatabase getDbInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (GestionaTuMenuDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GestionaTuMenuDatabase.class,
                            DATABASE_NAME
                    )
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}